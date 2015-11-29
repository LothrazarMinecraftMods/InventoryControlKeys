package com.lothrazar.samsinvcontrol;

import com.lothrazar.samsinvcontrol.net.*;
import com.lothrazar.samsinvcontrol.proxy.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
  
@Mod(modid = ModInventory.MODID, useMetadata=true, updateJSON="https://raw.githubusercontent.com/LothrazarMinecraftMods/InventoryControlKeys/master/update.json")  
public class ModInventory
{
	public static final String MODID = "samsinvcontrol"; 
	public static final String TEXTURE_LOCATION = MODID + ":"; 
	@Instance(value = MODID)
	public static ModInventory instance;
	@SidedProxy(clientSide="com.lothrazar.samsinvcontrol.proxy.ClientProxy", serverSide="com.lothrazar.samsinvcontrol.proxy.CommonProxy")
	public static CommonProxy proxy;  
	public static SimpleNetworkWrapper network;  
  
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{ 
    	network = NetworkRegistry.INSTANCE.newSimpleChannel( MODID );     	
    	
    	int packetID = 0;
    	network.registerMessage(MessageSlotUp.class, MessageSlotUp.class, packetID++, Side.SERVER);
    	network.registerMessage(MessageSlotDown.class, MessageSlotDown.class, packetID++, Side.SERVER);
    	network.registerMessage(MessageBarUp.class, MessageBarUp.class, packetID++, Side.SERVER);
    	network.registerMessage(MessageBarDown.class, MessageBarDown.class, packetID++, Side.SERVER);

		MinecraftForge.EVENT_BUS.register(instance);   
	}
        
	@EventHandler
	public void onInit(FMLInitializationEvent event)
	{      
		proxy.registerRenderers();
	}

	@SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) 
    {   
		
        if(ClientProxy.keyShiftUp.isPressed() )
        { 	     
        	 ModInventory.network.sendToServer( new MessageSlotUp()); 
        }        
        else if(ClientProxy.keyShiftDown.isPressed() )
        { 	      
        	 ModInventory.network.sendToServer( new MessageSlotDown());  
        }      
        else if(ClientProxy.keyBarUp.isPressed() )
        { 	      
        	 ModInventory.network.sendToServer( new MessageBarUp());  
        }   
        else if(ClientProxy.keyBarDown.isPressed() )
        { 	      
        	 ModInventory.network.sendToServer( new MessageBarDown());  
        }  
    } 
}
