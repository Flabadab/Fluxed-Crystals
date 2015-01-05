package fluxedCrystals.client.gui.gemCutter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fluxedCrystals.ModProps;
import fluxedCrystals.tileEntity.TileEntityGemCutter;

public class GUIGemCutter extends GuiContainer {
	private TileEntityGemCutter tile;

	public GUIGemCutter(InventoryPlayer invPlayer, TileEntityGemCutter tile2) {
		super(new ContainerGemCutter(invPlayer, tile2));

		xSize = 175;
		ySize = 166;
		this.tile = tile2;

	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/SeedInfuser.png");

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();

	}

	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft+115, guiTop+61, 0, 166, 18, 18);
		drawTexturedModalRect(guiLeft+133, guiTop+61, 0, 166, 18, 18);
		drawTexturedModalRect(guiLeft+151, guiTop+61, 0, 166, 18, 18);
		drawTexturedModalRect(guiLeft+75, guiTop+61, 0, 166, 18, 18);
		
		
	}

}
