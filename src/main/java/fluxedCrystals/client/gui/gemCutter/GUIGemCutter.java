package fluxedCrystals.client.gui.gemCutter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import fluxedCrystals.ModProps;
import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.tileEntity.TileEntityGemCutter;

public class GUIGemCutter extends GuiContainer {
	private TileEntityGemCutter tile;
	private int energyOffset = 0;
	private int cut = 0;

	public GUIGemCutter(InventoryPlayer invPlayer, TileEntityGemCutter tile2) {
		super(new ContainerGemCutter(invPlayer, tile2));

		xSize = 176;
		ySize = 166;
		this.tile = tile2;

	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/cutrefine.png");

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		cut = 0;
	}

	public void updateScreen() {
		super.updateScreen();
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		if (!tile.isUpgradeActive(new ItemStack(FCItems.upgradeMana)) && !tile.isUpgradeActive(new ItemStack(FCItems.upgradeEssentia)) && !tile.isUpgradeActive(new ItemStack(FCItems.upgradeLP))) {
			drawTexturedModalRect(guiLeft + 14, guiTop + 15, 177, 4, 14, 42);
			drawTexturedModalRect(guiLeft + 14, guiTop + 15 + cut, 193, 4 - cut, 14, 42 + cut);
		}
		if (tile.isUpgradeActive(new ItemStack(FCItems.upgradeMana))) {
			drawTexturedModalRect(guiLeft + 14, guiTop + 15, 177, 47, 14, 42 - tile.getManaRemaningScaled(42));

		}

	}

}
