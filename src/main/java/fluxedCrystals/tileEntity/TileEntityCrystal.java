package fluxedCrystals.tileEntity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.cbcore.LangUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cofh.api.energy.IEnergyContainerItem;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.compat.waila.IWailaInfo;
import fluxedCrystals.items.FCItems;

public class TileEntityCrystal extends TileEntity implements IWailaInfo {
	private int idx = 0;
	@Getter
	@Setter
	private int ticksgrown;

	@Getter
	@Setter
	private boolean harvested = false;

	public TileEntityCrystal() {

	}

	public void updateEntity() {
		ticksgrown++;
		if (ticksgrown >= RecipeRegistry.getGrowthTime(idx)) {
			ticksgrown = 0;
		}
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public int getIndex() {
		return idx;
	}

	public void init(int itemDamage) {
		this.idx = itemDamage;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("index", idx);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.idx = tag.getInteger("index");
	}

	@Override
	public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
		return oldBlock != newBlock;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void getWailaInfo(List<String> tooltip, int x, int y, int z, World world) {
		float growthValue = (world.getBlockMetadata(x, y, z) / 7.0F) * 100.0F;
		if (growthValue < 100.0)
			tooltip.add(String.format("%s : %.0f %%", LangUtil.translateG("hud.msg.growth"), growthValue));
		else
			tooltip.add(String.format("%s : %s", LangUtil.translateG("hud.msg.growth"), LangUtil.translateG("hud.msg.mature")));

	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return new ItemStack(FCItems.shard, RecipeRegistry.getSeedReturn(idx), getIndex());
	}
}
