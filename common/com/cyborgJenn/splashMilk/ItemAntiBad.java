package com.cyborgJenn.splashMilk;

import java.util.Iterator;

import com.cyborgJenn.splashMilk.utils.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAntiBad extends Item{
	public ItemAntiBad() {
		super();
		this.setTextureName(Reference.TEXTURE + "potion_bottle_milk");
		this.setMaxStackSize(3);
		//this.setContainerItem(Items.bucket);
		//this.setCreativeTab(CreativeTabs.tabBrewing);
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
                Iterator<PotionEffect> effect = player.getActivePotionEffects().iterator();
                
                while (effect.hasNext()) {
                        int potionID = effect.next().getPotionID();
                        if (potionID > 0 && potionID < Potion.potionTypes.length && Potion.potionTypes[potionID].isBadEffect())
                                effect.remove();
                }
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
