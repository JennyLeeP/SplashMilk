package com.cyborgJenn.splashMilk;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySplashMilk extends EntityPotion {

	/**
	 * The damage value of the thrown potion that this EntityPotion represents.
	 */
	private ItemStack potionDamage;
	
	//private EntityLivingBase livingBase;

	public EntitySplashMilk(World world,EntityLivingBase par2EntityLivingBase, ItemStack itemStack) {
		super(world, par2EntityLivingBase, itemStack);
		//livingBase = par2EntityLivingBase;
		// TODO Auto-generated constructor stub
	}

	public EntitySplashMilk(World par1World, EntityLivingBase par2EntityLivingBase, int par3)
	{
		this(par1World, par2EntityLivingBase, new ItemStack(Items.potionitem, 1, par3));
	}

	/**
	 * Gets the amount of gravity to apply to the thrown entity with each tick.
	 */
	@Override
	protected float getGravityVelocity()
	{
		return 0.05F;
	}

	@Override
	protected float func_70182_d()
	{
		return 0.5F;
	}

	@Override
	protected float func_70183_g()
	{
		return -20.0F;
	}

	@Override
	public void setPotionDamage(int par1)
	{
		if (this.potionDamage == null)
		{
			this.potionDamage = new ItemStack(Items.potionitem, 1, 14);
		}

		this.potionDamage.setItemDamage(par1);
	}

	/**
	 * Returns the damage value of the thrown potion that this EntityPotion represents.
	 */
	@Override
	public int getPotionDamage()
	{
		if (this.potionDamage == null)
		{
			this.potionDamage = new ItemStack(Items.potionitem, 1, 14);
		}

		return this.potionDamage.getItemDamage();
	}
	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if (!this.worldObj.isRemote)
        {
            List list = Items.potionitem.getEffects(this.potionDamage);

            if (list != null && !list.isEmpty())
            {
                AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
                List list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

                if (list1 != null && !list1.isEmpty())
                {
                    Iterator iterator = list1.iterator();

                    while (iterator.hasNext())
                    {
                        EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
                        double d0 = this.getDistanceSqToEntity(entitylivingbase);

                        if (d0 < 16.0D)
                        {
                            double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

                            if (entitylivingbase == par1MovingObjectPosition.entityHit)
                            {
                                d1 = 1.0D;
                            }

                            Iterator iterator1 = list.iterator();

                            entitylivingbase.clearActivePotions();
                        }
                    }
                }
            }

            this.worldObj.playAuxSFX(2002, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), this.getPotionDamage());
            this.setDead();
        }
	}

}


