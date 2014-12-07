package fluxedCrystals.items.armor;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.utils.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemShardHelm extends ItemArmor {

	public ItemShardHelm() {
		super(ArmorMaterial.IRON, 0, 0);
	}

	public int getRenderPasses(int metadata) {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list) {
		int numSeeds = RecipeRegistry.getNumSeedRecipes();
		for (int i = 0; i < numSeeds; i++) {
			if (RecipeRegistry.hasPrettyPrettyArmor(i)) {
				ItemStack stack = new ItemStack(this);
				NBTHelper.setInteger(stack, "color", RecipeRegistry.getColor(i));
				NBTHelper.setInteger(stack, "index", i);
				list.add(stack);
			}
		}
	}

	/**
	 * Return the color for the specified armor ItemStack.
	 */
	public int getColor(ItemStack p_82814_1_) {
		return NBTHelper.getInt(p_82814_1_, "color");
	}

	/**
	 * Return whether the specified armor ItemStack has a color.
	 */
	public boolean hasColor(ItemStack p_82816_1_) {
		return true;
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int par2) {
		if (stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
		}
		return NBTHelper.getInt(stack, "color");
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if (stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
		}
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), RecipeRegistry.getName(NBTHelper.getInt(stack, "index")));
	}

}
