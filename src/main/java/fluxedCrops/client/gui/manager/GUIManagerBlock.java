package fluxedCrops.client.gui.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fluxedCrops.ModProps;
import fluxedCrops.network.MessageManagerBlock;
import fluxedCrops.network.PacketHandler;
import fluxedCrops.tileEntity.TileEntityManagerBlock;

public class GUIManagerBlock extends GuiContainer {

	private TileEntityManagerBlock tile;


	public GUIManagerBlock(InventoryPlayer invPlayer, TileEntityManagerBlock tile2) {
		super(new ContainerManagerBlock(invPlayer, tile2));

		xSize = 175;
		ySize = 175;
		this.tile = tile2;

	}

	public void updateScreen() {
	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/Manager.png");

	public void initGui() {
		super.initGui();
		buttonList.add(new GuiButton(0, guiLeft + 50, guiTop + 50, 23, 20, "3x3"));
		buttonList.add(new GuiButton(1, guiLeft + 78, guiTop + 50, 23, 20, "5x5"));
		buttonList.add(new GuiButton(2, guiLeft + 106, guiTop + 50, 23, 20, "7x7"));
		buttonList.add(new GuiButton(3, guiLeft + 134, guiTop + 50, 23, 20, "9x9"));
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {

		case 0:
			if (!tile.canPlacePowerBlocks()) {
				
			} else if (tile.canPlacePowerBlocks(1)) {
				tile.placePowerBlocks(1);
				PacketHandler.INSTANCE.sendToServer(new MessageManagerBlock(tile.xCoord, tile.yCoord, tile.zCoord, 1));
			}
			break;
		case 1:
			if (!tile.canPlacePowerBlocks()) {

			} else if (tile.canPlacePowerBlocks(2)) {
				tile.placePowerBlocks(2);
				PacketHandler.INSTANCE.sendToServer(new MessageManagerBlock(tile.xCoord, tile.yCoord, tile.zCoord, 2));
			}
			break;
			
		case 2:
			if (!tile.canPlacePowerBlocks()) {
				
			} else if (tile.canPlacePowerBlocks(3)) {
				tile.placePowerBlocks(3);
				PacketHandler.INSTANCE.sendToServer(new MessageManagerBlock(tile.xCoord, tile.yCoord, tile.zCoord, 3));
			}
			break;
		case 3:
			if (!tile.canPlacePowerBlocks()) {

			} else if (tile.canPlacePowerBlocks(4)) {
				tile.placePowerBlocks(4);
				PacketHandler.INSTANCE.sendToServer(new MessageManagerBlock(tile.xCoord, tile.yCoord, tile.zCoord, 4));
			}
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

