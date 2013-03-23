package kraffs.culinarycraft;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCoffeeGrinder extends Container {

	protected TileEntityCoffeeGrinder tileEntity;
	private EntityPlayer player;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
	
	public ContainerCoffeeGrinder(InventoryPlayer inventoryPlayer, TileEntityCoffeeGrinder te) {
		lastCookTime = 0;
		lastBurnTime = 0;
		lastItemBurnTime = 0;
		tileEntity = te;
/*		addSlotToContainer(new Slot(te, 0, 56, 53));
		addSlotToContainer(new Slot(te, 1, 56, 17));
		//addSlotToContainer(new Slot(te, 2, 112, 32));
		addSlotToContainer(new SlotCoffeeGrinder(inventoryPlayer.player, te, 2, 112, 32));*/
		addSlotToContainer(new Slot(te, 0, 90, 56)); // Input
		addSlotToContainer(new Slot(te, 1, 54, 56)); // Fuel
		addSlotToContainer(new SlotCoffeeGrinder(inventoryPlayer.player, te, 2, 51, 17)); // Output
		
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(inventoryPlayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		
		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(inventoryPlayer, j, 8 + j * 18, 142));
		}
	}
	
	public void detectAndSendChanges()
	{
	         super.detectAndSendChanges();
	         Iterator var1 = this.crafters.iterator();
	         while (var1.hasNext())
	         {
	                 ICrafting var2 = (ICrafting)var1.next();
	                 if (this.lastCookTime != this.tileEntity.CookTime)
	                 {
	                         var2.sendProgressBarUpdate(this, 0, this.tileEntity.CookTime);
	                 }
	                 if (this.lastBurnTime != this.tileEntity.BurnTime)
	                 {
	                         var2.sendProgressBarUpdate(this, 1, this.tileEntity.BurnTime);
	                 }
	                 if (this.lastItemBurnTime != this.tileEntity.ItemBurnTime)
	                 {
	                         var2.sendProgressBarUpdate(this, 2, this.tileEntity.ItemBurnTime);
	                 }
	         }
	         this.lastCookTime = this.tileEntity.CookTime;
	         this.lastBurnTime = this.tileEntity.BurnTime;
	         this.lastItemBurnTime = this.tileEntity.ItemBurnTime;
	}
	
	public void updateProgressBar(int par1, int par2)
	{
	         if (par1 == 0)
	         {
	                 tileEntity.CookTime = par2;
	         }

	         if (par1 == 1)
	         {
	                 tileEntity.BurnTime = par2;
	         }

	         if (par1 == 2)
	         {
	                 tileEntity.ItemBurnTime = par2;
	         }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
	

	
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber)
    {
             ItemStack itemstack = null;
             Slot slot = (Slot)inventorySlots.get(slotnumber);

             if (slot != null && slot.getHasStack())
             {
                     ItemStack itemstack1 = slot.getStack();
                     itemstack = itemstack1.copy();

                     if (slotnumber == 2)
                     {
                             if (!mergeItemStack(itemstack1, 3, 39, true))
                             {
                                     return null;
                             }

                             slot.onSlotChange(itemstack1, itemstack);
                     }
                     else if (slotnumber == 1 || slotnumber == 0)
                     {
                             if (!mergeItemStack(itemstack1, 3, 39, false))
                             {
                                     return null;
                             }
                     }
                     else if (RecipesCoffeeGrinder.smelting().getSmeltingResult(itemstack1.getItem().itemID) != null)
                     {
                             if (!mergeItemStack(itemstack1, 0, 1, false))
                             {
                                     return null;
                             }
                     }
                     else if (TileEntityCoffeeGrinder.isItemFuel(itemstack1))
                     {
                             if (!mergeItemStack(itemstack1, 1, 2, false))
                             {
                                     return null;
                             }
                     }
                     else if (slotnumber >= 3 && slotnumber < 30)
                     {
                             if (!mergeItemStack(itemstack1, 30, 39, false))
                             {
                                     return null;
                             }
                     }
                     else if (slotnumber >= 30 && slotnumber < 39 && !mergeItemStack(itemstack1, 3, 30, false))
                     {
                             return null;
                     }

                     if (itemstack1.stackSize == 0)
                     {
                             slot.putStack(null);
                     }
                     else
                     {
                             slot.onSlotChanged();
                     }

                     if (itemstack1.stackSize == itemstack.stackSize)
                     {
                             return null;
                     }
                    
                     slot.onPickupFromSlot(player, itemstack);
             }

             return itemstack;
    }

}
