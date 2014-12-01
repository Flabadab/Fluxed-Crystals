package fluxedCrops.utils;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import cpw.mods.fml.common.network.NetworkRegistry;
import fluxedCrops.network.MessageBiome;
import fluxedCrops.network.PacketHandler;

public class Utils {


	public static void setBiomeAt(World world, int x, int z, BiomeGenBase biome) {
		if (biome == null) {
			return;
		}
		Chunk chunk = world.getChunkFromBlockCoords(x, z);
		byte[] array = chunk.getBiomeArray();
		array[((z & 0xF) << 4 | x & 0xF)] = ((byte) (biome.biomeID & 0xFF));
		chunk.setBiomeArray(array);
		if (!world.isRemote) {
			PacketHandler.INSTANCE.sendToAllAround(new MessageBiome(x, z, biome.biomeID), new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, world.getHeightValue(x, z), z, 32.0D));
		}
	}
}
