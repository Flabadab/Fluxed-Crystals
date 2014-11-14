package fluxedCrops.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import fluxedCrops.blocks.Crop;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.tileEntity.TileEntityCrop;
import fluxedCrops.utils.NBTHelper;

/**
 * Created by Jared on 11/2/2014.
 */
public class Seeds extends Item implements IPlantable {

	public Seeds() {
		super();
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("x" + NBTHelper.getString(stack, "material"));
	}

	public int getRenderPasses(int metadata) {
		return 1;
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int par2) {
		return NBTHelper.getInt(stack, "color");
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return FCBlocks.crop;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {

		if (world.getBlock(x, y, z) == FCBlocks.powerBlock) {
			if (hitY == 1.0F) {
				world.setBlock(x, y + 1, z, new Crop());
				
				--stack.stackSize;
				return true;
			}
		}
		return false;
	}

}
