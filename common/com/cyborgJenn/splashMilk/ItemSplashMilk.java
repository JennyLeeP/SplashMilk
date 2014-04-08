package com.cyborgJenn.splashMilk;

import com.cyborgJenn.splashMilk.utils.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.world.World;

public class ItemSplashMilk extends Item{
	
	public ItemSplashMilk() {
		super();
		this.setTextureName(Reference.TEXTURE + "potion_bottle_splashmilk");
		this.setMaxStackSize(64);
		//this.setContainerItem(Items.bucket);
		this.setCreativeTab(CreativeTabs.tabBrewing);
	}
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (isSplash(itemStack.getItemDamage()))
        {
            if (!player.capabilities.isCreativeMode)
            {
                --itemStack.stackSize;
            }

            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!world.isRemote)
            {
            	world.spawnEntityInWorld(new EntitySplashMilk(world, player, itemStack));
            }

            return itemStack;
        }
        else
        {
        	player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
            return itemStack;
        }
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        return false;
    }
    /**
     * returns wether or not a potion is a throwable splash potion based on damage value
     */
    public static boolean isSplash(int par0)
    {
        return true;
    }
    
}
