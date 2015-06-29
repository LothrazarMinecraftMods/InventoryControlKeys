package com.lothrazar.samsinvcontrol;

import org.apache.logging.log4j.Logger;     
import com.lothrazar.samsinvcontrol.proxy.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
        	 ModInventory.network.sendToServer( new MessageSlotUp(ClientProxy.keyShiftUp.getKeyCode()));  
        }        
        else if(ClientProxy.keyShiftDown.isPressed() )
        { 	      
        	 ModInventory.network.sendToServer( new MessageSlotDown(ClientProxy.keyShiftDown.getKeyCode()));  
        }      
        else if(ClientProxy.keyBarUp.isPressed() )
        { 	      
        	 ModInventory.network.sendToServer( new MessageBarUp(ClientProxy.keyBarUp.getKeyCode()));  
        }   
        else if(ClientProxy.keyBarDown.isPressed() )
        { 	      
        	 ModInventory.network.sendToServer( new MessageBarDown(ClientProxy.keyBarDown.getKeyCode()));  
        }  

    } 
}
