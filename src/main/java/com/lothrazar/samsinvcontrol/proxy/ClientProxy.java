package com.lothrazar.samsinvcontrol.proxy;

import org.lwjgl.input.Keyboard;   

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class ClientProxy extends CommonProxy 
{  
	public static KeyBinding keyShiftUp;
	public static KeyBinding keyShiftDown; 
	public static KeyBinding keyBarUp;
	public static KeyBinding keyBarDown; 
	public static final String keyUpName = "key.columnshiftup";
	public static final String keyDownName = "key.columnshiftdown"; 
	public static final String keyBarUpName = "key.columnbarup";
	public static final String keyBarDownName = "key.columnbardown"; 
 
	public static final String keyCategoryInventory = "key.categories.inventorycontrol";
	
    @Override
    public void registerRenderers() 
    {  
    	registerKeyBindings();  
    }
  
	private void registerKeyBindings() 
	{
		keyShiftUp = new KeyBinding(keyUpName, Keyboard.KEY_Y, keyCategoryInventory);
        ClientRegistry.registerKeyBinding(ClientProxy.keyShiftUp);
    
		keyShiftDown = new KeyBinding(keyDownName, Keyboard.KEY_H, keyCategoryInventory); 
        ClientRegistry.registerKeyBinding(ClientProxy.keyShiftDown); 

        keyBarUp = new KeyBinding(keyBarUpName, Keyboard.KEY_U, keyCategoryInventory);
        ClientRegistry.registerKeyBinding(ClientProxy.keyBarUp);
         
        keyBarDown = new KeyBinding(keyBarDownName, Keyboard.KEY_J, keyCategoryInventory); 
        ClientRegistry.registerKeyBinding(ClientProxy.keyBarDown);

        
         
	} 

}
