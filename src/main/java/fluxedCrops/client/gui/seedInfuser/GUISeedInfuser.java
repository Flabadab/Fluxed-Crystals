package fluxedCrops.client.gui.seedInfuser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fluxedCrops.ModProps;
import fluxedCrops.network.MessageSeedInfuser;
import fluxedCrops.network.PacketHandler;
import fluxedCrops.tileEntity.TileEntitySeedInfuser;

public class GUISeedInfuser extends GuiContainer {

	private TileEntitySeedInfuser tile;

	public GUISeedInfuser(InventoryPlayer invPlayer, TileEntitySeedInfuser tile2) {
		super(new ContainerSeedInfuser(invPlayer, tile2));

		xSize = 175;
		ySize = 175;
		this.tile = tile2;

	}

	public void updateScreen() {
	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/SeedInfuser.png");

	public void initGui() {
		super.initGui();
		buttonList.add(new GuiButton(0, guiLeft + 50, guiTop + 50, 46, 20, "Infuse"));
		
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {

		case 0:
			tile.infuseSeed();
			PacketHandler.INSTANCE.sendToServer(new MessageSeedInfuser(tile.xCoord, tile.yCoord, tile.zCoord));
			break;

		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	}

}
