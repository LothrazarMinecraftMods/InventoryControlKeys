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

public class MessageBarDown implements IMessage, IMessageHandler<MessageBarDown, IMessage>
{
	private byte keyPressed;
	  
	public static final int ID = 4;
	public MessageBarDown()
	{ 
	}
	
	public MessageBarDown(int keyCode)
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
	public IMessage onMessage(MessageBarDown message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().playerEntity; 
 
		UtilPlayerInventory.shiftSlotDown(player, 0); 
		UtilPlayerInventory.shiftSlotDown(player, 1); 
		UtilPlayerInventory.shiftSlotDown(player, 2); 
		UtilPlayerInventory.shiftSlotDown(player, 3); 
		UtilPlayerInventory.shiftSlotDown(player, 4); 
		UtilPlayerInventory.shiftSlotDown(player, 5); 
		UtilPlayerInventory.shiftSlotDown(player, 6); 
		UtilPlayerInventory.shiftSlotDown(player, 7); 
		UtilPlayerInventory.shiftSlotDown(player, 8); 
 	
		return null;
	}
}
 
