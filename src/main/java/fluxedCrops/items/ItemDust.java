package fluxedCrops.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDust extends Item {

	private int color;

	public void setColor(int color) {
		this.color = color;
	}

	public int getRenderPasses(int metadata) {
		return 1;
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return color;
	}
}
