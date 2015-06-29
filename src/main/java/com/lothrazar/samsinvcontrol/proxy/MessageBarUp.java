package com.lothrazar.samsinvcontrol.proxy;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageBarUp implements IMessage, IMessageHandler<MessageBarUp, IMessage>
{
	//private byte keyPressed;
	  
	public static final int ID = 3;
	public MessageBarUp()
	{ 
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		//this.keyPressed = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		//buf.writeByte(keyPressed);
	}
	
	@Override
	public IMessage onMessage(MessageBarUp message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().playerEntity; 
 
		UtilPlayerInventory.shiftSlotUp(player, 0); 
		UtilPlayerInventory.shiftSlotUp(player, 1); 
		UtilPlayerInventory.shiftSlotUp(player, 2); 
		UtilPlayerInventory.shiftSlotUp(player, 3); 
		UtilPlayerInventory.shiftSlotUp(player, 4); 
		UtilPlayerInventory.shiftSlotUp(player, 5); 
		UtilPlayerInventory.shiftSlotUp(player, 6); 
		UtilPlayerInventory.shiftSlotUp(player, 7); 
		UtilPlayerInventory.shiftSlotUp(player, 8); 
 	 
		return null;
	}
}
 
