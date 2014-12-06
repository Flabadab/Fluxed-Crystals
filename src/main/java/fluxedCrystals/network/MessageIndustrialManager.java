package fluxedCrystals.network;

import fluxedCrystals.tileEntity.IManager;
import fluxedCrystals.tileEntity.TileEntityIndustrialManager;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageIndustrialManager implements IMessage, IMessageHandler<MessageIndustrialManager, IMessage> {
	public int x, y, z;
	public int size;

	public MessageIndustrialManager() {
	}

	public MessageIndustrialManager(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageIndustrialManager(int x, int y, int z, int size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.size = buf.readInt();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(size);
	}

	@Override
	public IMessage onMessage(MessageIndustrialManager message, MessageContext ctx) {
		TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEntityIndustrialManager) {
			((TileEntityIndustrialManager) te).placePowerBlocks(message.size);
		}
		return null;
	}
}