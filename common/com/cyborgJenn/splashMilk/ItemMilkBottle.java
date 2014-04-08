package com.cyborgJenn.splashMilk;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.cyborgJenn.splashMilk.utils.Reference;

public class ItemMilkBottle extends Item{
	public ItemMilkBottle() {
		super();
		this.setTextureName(Reference.TEXTURE + "potion_bottle_milk");
		this.setMaxStackSize(64);
		//this.setContainerItem(Items.bucket);
		this.setCreativeTab(CreativeTabs.tabBrewing);
	}
	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }

        if (!world.isRemote)
        {
        	player.clearActivePotions();
        	player.removePotionEffect(Potion.poison.getId());
        }

        return itemStack.stackSize <= 0 ? new ItemStack(Items.glass_bottle) : itemStack;
    }
	/**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.drink;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
}
