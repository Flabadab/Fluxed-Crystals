package fluxedCrops.network;

import fluxedCrops.FluxedCrops;
import fluxedCrops.utils.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageBiome implements IMessage, IMessageHandler<MessageBiome, IMessage> {
	private int x;
	private int z;
	private int biome;

	public MessageBiome() {
	}

	public MessageBiome(int x, int z, int biome) {
		this.x = x;
		this.z = z;
		this.biome = biome;
	}

	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(this.x);
		buffer.writeInt(this.z);
		buffer.writeShort(this.biome);
	}

	public void fromBytes(ByteBuf buffer) {
		this.x = buffer.readInt();
		this.z = buffer.readInt();
		this.biome = buffer.readShort();
	}

	public IMessage onMessage(MessageBiome message, MessageContext ctx) {
		Utils.setBiomeAt(FluxedCrops.proxy.getClientWorld(), message.x, message.z, BiomeGenBase.getBiome(message.biome));
		return null;
	}
}
