package fluxedCrystals.network;

import fluxedCrystals.tileEntity.IManager;
import fluxedCrystals.tileEntity.TileEntityBloodManager;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageBloodManager implements IMessage, IMessageHandler<MessageBloodManager, IMessage> {
	public int x, y, z;
	public int size;

	public MessageBloodManager() {
	}

	public MessageBloodManager(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageBloodManager(int x, int y, int z, int size) {
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
	public IMessage onMessage(MessageBloodManager message, MessageContext ctx) {
		TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEntityBloodManager) {
			((TileEntityBloodManager) te).placePowerBlocks(message.size);
		}
		return null;
	}
}