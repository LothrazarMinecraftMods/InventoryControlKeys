package com.lothrazar.samsinvcontrol.proxy;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class MessageSlotUp implements IMessage, IMessageHandler<MessageSlotUp, IMessage>
{
	private byte keyPressed;
	  
	public static final int ID = 0;
	public MessageSlotUp()
	{ 
	}
	
	public MessageSlotUp(int keyCode)
	{ 
		this.keyPressed = (byte)keyCode;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.keyPressed = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(keyPressed);
	}
	
	@Override
	public IMessage onMessage(MessageSlotUp message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().playerEntity; 
 
		UtilPlayerInventory.shiftSlotUp(player, player.inventory.currentItem); 
		 
		return null;
	}

	
}
 