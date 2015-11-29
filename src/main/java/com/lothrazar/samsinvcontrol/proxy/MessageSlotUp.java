package com.lothrazar.samsinvcontrol.proxy;

import com.lothrazar.samsinvcontrol.ModInventory;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageSlotUp implements IMessage, IMessageHandler<MessageSlotUp, IMessage>
{
	//private byte keyPressed;
	  
	public static final int ID = 0;
	public MessageSlotUp()
	{ 
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		//buf.writeByte(keyPressed);
	}
	
	@Override
	public IMessage onMessage(MessageSlotUp message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().playerEntity; 
		ModInventory.instance.addChat(player, "Message slot up start");
 
		UtilPlayerInventory.shiftSlotUp(player, player.inventory.currentItem); 

		ModInventory.instance.addChat(player, "Message slot up start");
		return null;
	}
}
 
