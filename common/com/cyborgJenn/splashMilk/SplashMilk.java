package com.cyborgJenn.splashMilk;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.cyborgJenn.splashMilk.proxy.CommonProxy;
import com.cyborgJenn.splashMilk.utils.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class SplashMilk {

	@Instance(Reference.NAME)
	public static SplashMilk  instance;
	
	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
	public static CommonProxy proxy;
	
	public static Item itemMilkBottle;
	public static Item itemSplashMilk;
	public static Item itemAntiBad;
	public static Item itemAntiBadSplash;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		allStuffs();
		
		System.out.println("[SplashMilk] " + "Pre Init Complete..........");

	}
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{


		System.out.println("[SplashMilk] " + "Init Complete.............");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		

		System.out.println("[SplashMilk] " + "Post Init Complete.............");
	}
	
	public void allStuffs(){
		
		itemMilkBottle = new ItemMilkBottle().setUnlocalizedName("ItemMilkBottle");
		itemSplashMilk = new ItemSplashMilk().setUnlocalizedName("ItemSplashMilk");
		
		itemAntiBad = new ItemAntiBad().setUnlocalizedName("ItemAntiBad");
		itemAntiBadSplash = new ItemAntiBadSplash().setUnlocalizedName("ItemAntiBadSplash");
		
		GameRegistry.registerItem(itemMilkBottle, "ItemMilkBottle");
		GameRegistry.registerItem(itemSplashMilk, "ItemSplashMilk");
		GameRegistry.registerItem(itemAntiBad, "ItemAntiBad");
		GameRegistry.registerItem(itemAntiBadSplash, "ItemAntiBadSplash");
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemMilkBottle, 3, 0), new Object[] {new ItemStack(Items.milk_bucket, 1, 0),new ItemStack(Items.glass_bottle),new ItemStack(Items.glass_bottle),new ItemStack(Items.glass_bottle)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSplashMilk, 3, 0), new Object[] {new ItemStack(Items.gunpowder),new ItemStack(itemMilkBottle),new ItemStack(itemMilkBottle),new ItemStack(itemMilkBottle)});
		
	}
}
