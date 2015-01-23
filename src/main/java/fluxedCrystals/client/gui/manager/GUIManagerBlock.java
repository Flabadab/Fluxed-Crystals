package fluxedCrystals.client.gui.manager;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fluxedCrystals.ModProps;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;

public class GUIManagerBlock extends GuiContainer {

	private TileEntityManagerBlock tile;

	public GUIManagerBlock(InventoryPlayer invPlayer, TileEntityManagerBlock tile2) {
		super(new ContainerManagerBlock(invPlayer, tile2));

		xSize = 176;
		ySize = 166;
		this.tile = tile2;

	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/CustomManager.png");

	public void initGui() {
		super.initGui();

	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft + 14, guiTop + 15, 177, 4, 14, 42);
		if (tile.getEnergyStored() > 0 && !tile.isUpgradeActive(new ItemStack(FCItems.upgradeMana)) && !tile.isUpgradeActive(new ItemStack(FCItems.upgradeEssentia)) && !tile.isUpgradeActive(new ItemStack(FCItems.upgradeLP))) {
			GL11.glColor4d(tile.getEnergyColor() / tile.getMaxStorage(), tile.getEnergyColor() / tile.getMaxStorage(), tile.getEnergyColor() / tile.getMaxStorage(), 1f);
			drawTexturedModalRect(guiLeft + 14, guiTop + 15, 193, 4, 14, 42);
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		}
		if(tile.isUpgradeActive(new ItemStack(FCItems.upgradeMana))){
			drawTexturedModalRect(guiLeft + 14, guiTop + 15, 193, 47, 14, 42);
		}
		if(tile.isUpgradeActive(new ItemStack(FCItems.upgradeLP))){
			drawTexturedModalRect(guiLeft + 14, guiTop + 15, 193, 90, 14, 42);
		}
		if(tile.isUpgradeActive(new ItemStack(FCItems.upgradeEssentia))){
			drawTexturedModalRect(guiLeft + 14, guiTop + 15, 193, 133, 14, 42);
		}
		
		
		// if (tile.isUpgradeActive(new ItemStack(FCItems.upgradeMana))) {
		// GL11.glColor4d(tile.getManaColor() / 10000, tile.getManaColor() /
		// 10000, tile.getManaColor() / 10000, 1f);

		// drawTexturedModalRect(guiLeft + 14, guiTop + 15, 193, 47, 14, 42);
		// GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		// }

	}

}
