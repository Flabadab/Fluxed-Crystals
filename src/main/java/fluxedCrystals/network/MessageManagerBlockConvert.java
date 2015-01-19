package fluxedCrystals.network;

import fluxedCrystals.tileEntity.IManager;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageManagerBlockConvert implements IMessage, IMessageHandler<MessageManagerBlockConvert, IMessage> {
	public int x, y, z;
	public boolean  convert;

	public MessageManagerBlockConvert() {
	}

	public MessageManagerBlockConvert(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageManagerBlockConvert(int x, int y, int z, boolean convert) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.convert= convert;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.convert = buf.readBoolean();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(convert);
	}

	@Override
	public IMessage onMessage(MessageManagerBlockConvert message, MessageContext ctx) {
		TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEntityManagerBlock) {
			((TileEntityManagerBlock) te).setConverting(true);;
		}
		return null;
	}
}