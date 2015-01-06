package fluxedCrystals.client.gui.gemRefiner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fluxedCrystals.ModProps;
import fluxedCrystals.client.gui.seedInfuser.ContainerSeedInfuser;
import fluxedCrystals.network.MessageGemRefiner;
import fluxedCrystals.network.MessageSeedInfuser;
import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;

public class GUIGemRefiner extends GuiContainer {

	private TileEntityGemRefiner tile;

	public GUIGemRefiner(InventoryPlayer invPlayer, TileEntityGemRefiner tile2) {
		super(new ContainerGemRefiner(invPlayer, tile2));

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
	public void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft+115, guiTop+61, 0, 166, 18, 18);
		drawTexturedModalRect(guiLeft+133, guiTop+61, 0, 166, 18, 18);
		drawTexturedModalRect(guiLeft+151, guiTop+61, 0, 166, 18, 18);
		drawTexturedModalRect(guiLeft+75, guiTop+61, 0, 166, 18, 18);
		
	}

}
