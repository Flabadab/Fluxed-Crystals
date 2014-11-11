package fluxedCrops.network;

import fluxedCrops.tileEntity.TileEntitySeedInfuser;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSeedInfuser implements IMessage, IMessageHandler<MessageSeedInfuser, IMessage> {
	public int x, y, z;

	public MessageSeedInfuser() {
	}

	public MessageSeedInfuser(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageSeedInfuser(int x, int y, int z, int size) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public IMessage onMessage(MessageSeedInfuser message, MessageContext ctx) {
		TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEntitySeedInfuser) {
			((TileEntitySeedInfuser) te).infuseSeed();
		}
		return null;
	}
}