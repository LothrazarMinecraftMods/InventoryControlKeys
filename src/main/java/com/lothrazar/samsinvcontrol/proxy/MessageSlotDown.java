package com.lothrazar.samsinvcontrol.proxy;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageSlotDown implements IMessage, IMessageHandler<MessageSlotDown, IMessage>
{
	public MessageSlotDown()	{ 	}

	@Override
	public void fromBytes(ByteBuf buf)	{	}
	
	@Override
	public void toBytes(ByteBuf buf)	{	}
	
	@Override
	public IMessage onMessage(MessageSlotDown message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().playerEntity; 
 
		UtilPlayerInventory.shiftSlotDown(player, player.inventory.currentItem); 
		 
		return null;
	}
}
 
