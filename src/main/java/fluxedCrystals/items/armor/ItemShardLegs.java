package fluxedCrystals.items.armor;

import java.util.List;

import codechicken.nei.api.API;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.utils.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class ItemShardLegs extends ItemArmor {

	public ItemShardLegs() {
		super(ArmorMaterial.IRON, 0, 2);
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
				RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(new ItemStack(Items.iron_boots), new ItemStack(FCItems.seed, 1, i), stack, RecipeRegistry.getIngredientAmount(i)));
				API.hideItem(stack);
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
