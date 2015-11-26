package com.lothrazar.samsinvcontrol;

import org.apache.logging.log4j.Logger;     
import com.lothrazar.samsinvcontrol.proxy.*;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
  
@Mod(modid = ModInventory.MODID, useMetadata=true)  
public class ModInventory
{
	public static final String MODID = "samsinvcontrol"; 
	public static final String TEXTURE_LOCATION = MODID + ":"; 
	@Instance(value = MODID)
	public static ModInventory instance;
	@SidedProxy(clientSide="com.lothrazar.samsinvcontrol.proxy.ClientProxy", serverSide="com.lothrazar.samsinvcontrol.proxy.CommonProxy")
	public static CommonProxy proxy;   
	public static Logger logger; 
	public static SimpleNetworkWrapper network;  
  
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{ 
		logger = event.getModLog();  
	
    	network = NetworkRegistry.INSTANCE.newSimpleChannel( MODID );     	
    	
    	network.registerMessage(MessageSlotUp.class, MessageSlotUp.class, MessageSlotUp.ID, Side.SERVER);
    	network.registerMessage(MessageSlotDown.class, MessageSlotDown.class, MessageSlotDown.ID, Side.SERVER);
    	network.registerMessage(MessageBarUp.class, MessageBarUp.class, MessageBarUp.ID, Side.SERVER);
    	network.registerMessage(MessageBarDown.class, MessageBarDown.class, MessageBarDown.ID, Side.SERVER);

		FMLCommonHandler.instance().bus().register(instance); 
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
        	 ModInventory.network.sendToServer( new MessageSlotUp());  //ClientProxy.keyShiftUp.getKeyCode()
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
