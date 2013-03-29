package kraffs.culinarycraft.tileentity;

import kraffs.culinarycraft.recipe.RecipesCoffeeGrinder;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesCrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;


public class TileEntityCoffeeGrinder extends TileEntity implements IInventory {

	private ItemStack inv[];
	
	public int BurnTime;
	private boolean isActive;
	
	public int ItemBurnTime;
	public int CookTime;
	public int front;
	
	public TileEntityCoffeeGrinder() {
		super();
		inv = new ItemStack[3];
		BurnTime = 0;
		ItemBurnTime = 0;
		CookTime = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void setFrontDirection(int f) {
		this.front = f;
	}
	
	public int getFrontDirection() {
		return this.front;
	}
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}
	
    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
             if (inv[par1] != null)
             {
                     if (inv[par1].stackSize <= par2)
                     {
                             ItemStack itemstack = inv[par1];
                             inv[par1] = null;
                             return itemstack;
                     }

                     ItemStack itemstack1 = inv[par1].splitStack(par2);

                     if (inv[par1].stackSize == 0)
                     {
                             inv[par1] = null;
                     }

                     return itemstack1;
             }
             else
             {
                     return null;
             }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
            //ItemStack stack = getStackInSlot(slot);
            if (inv[slot] != null) {
                    //setInventorySlotContents(slot, null);
            		ItemStack stack = inv[slot];
            		inv[slot] = null;
            		return stack;
            } else {
            	return null;
            }
           
    }
    
    @Override
    public int getInventoryStackLimit() {
            return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (worldObj == null)
        {
            return true;
        }
        if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        }
        return player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
    }

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
             super.readFromNBT(par1NBTTagCompound);
             NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
             inv = new ItemStack[getSizeInventory()];

             for (int i = 0; i < nbttaglist.tagCount(); i++)
             {
                     NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
                     byte byte0 = nbttagcompound.getByte("Slot");

                     if (byte0 >= 0 && byte0 < inv.length)
                     {
                             inv[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
                     }
             }
            
             front = par1NBTTagCompound.getInteger("FrontDirection");
             BurnTime = par1NBTTagCompound.getShort("BurnTime");
             CookTime = par1NBTTagCompound.getShort("CookTime");
             ItemBurnTime = getItemBurnTime(inv[1]);
            
             System.out.println("front:" + front);
    }
/*    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            NBTTagList tagList = tagCompound.getTagList("Items");
            inv = new ItemStack[getSizeInventory()];
            
            for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                    byte slot = tag.getByte("Slot");
                    if (slot >= 0 && slot < inv.length) {
                            inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                    }
            }
            
            front = tagCompound.getInteger("FrontDirection");
            BurnTime = tagCompound.getShort("BurnTime");
            CookTime = tagCompound.getShort("CookTime");
            ItemBurnTime = getItemBurnTime(inv[1]);
            
            System.out.println("front:" + front);
    }*/

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
             super.writeToNBT(par1NBTTagCompound);
             par1NBTTagCompound.setInteger("FrontDirection", (int)front);
             par1NBTTagCompound.setShort("BurnTime", (short)BurnTime);
             par1NBTTagCompound.setShort("CookTime", (short)CookTime);
             NBTTagList nbttaglist = new NBTTagList();

             for (int i = 0; i < inv.length; i++)
             {
                     if (inv[i] != null)
                     {
                             NBTTagCompound nbttagcompound = new NBTTagCompound();
                             nbttagcompound.setByte("Slot", (byte)i);
                             inv[i].writeToNBT(nbttagcompound);
                             nbttaglist.appendTag(nbttagcompound);
                     }
             }

             par1NBTTagCompound.setTag("Items", nbttaglist);
             System.out.println("write:" + front);
             System.out.println("burn:" + BurnTime);
    }
/*    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            tagCompound.setInteger("FrontDirection", (int)front);
            tagCompound.setShort("BurnTime", (short)BurnTime);
            tagCompound.setShort("CookTime", (short)CookTime);
                            
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < inv.length; i++) {
                    ItemStack stack = inv[i];
                    if (stack != null) {
                            NBTTagCompound tag = new NBTTagCompound();
                            tag.setByte("Slot", (byte) i);
                            stack.writeToNBT(tag);
                            itemList.appendTag(tag);
                    }
            }
            tagCompound.setTag("Items", itemList);
            System.out.println("Write:"+front);
            System.out.println("Burn:"+BurnTime);
    }*/
    
    @Override
    public boolean isInvNameLocalized() {
    	return false;
    }
    
    @Override
    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
    	return false;
    }
    
    @Override
    public String getInvName() {
    	return "container.coffeegrinder";
    }
	
    public int getCookProgressScaled(int par1) {
    	return (CookTime * par1) / 200;
    }
    
    public int getBurnTimeRemainingScaled(int par1) {
    	if (ItemBurnTime == 0) {
    		ItemBurnTime = 200;
    	}
    	
    	return (BurnTime * par1) / ItemBurnTime;
    }
    
    public boolean isBurning() {
    	return BurnTime > 0;
    }
    
    public void updateEntity()
    {
             boolean var1 = this.BurnTime > 0;
             boolean var2 = false;
             if (this.BurnTime > 0)
             {
                     --this.BurnTime;
             }
             if (!this.worldObj.isRemote)
             {
                     if (this.BurnTime == 0 && this.canSmelt())
                     {
                             this.ItemBurnTime = this.BurnTime = getItemBurnTime(this.inv[1]);
                             if (this.BurnTime > 0)
                             {
                                     var2 = true;
                                     if (this.inv[1] != null)
                                     {
                                             --this.inv[1].stackSize;
                                             if (this.inv[1].stackSize == 0)
                                             {
                                                     Item var3 = this.inv[1].getItem().getContainerItem();
                                                     this.inv[1] = var3 == null ? null : new ItemStack(var3);
                                             }
                                     }
                             }
                     }
                     if (this.isBurning() && this.canSmelt())
                     {
                             ++this.CookTime;
                             if (this.CookTime == 200)
                             {
                                     this.CookTime = 0;
                                     this.smeltItem();
                                     var2 = true;
                             }
                     }
                     else
                     {
                             this.CookTime = 0;
                     }
                     if (var1 != this.BurnTime > 0)
                     {
                             var2 = true;
                             this.validate();
                     }
             }
             boolean check = isActive;
             isActive = isBurning();
             if(isActive != check)
             {
                     this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
             }
             if (var2)
             {
                     this.onInventoryChanged();
             }
    }
    
    private boolean canSmelt() {
    	if (inv[0] == null) {
    		return false;
    	}
    	
    	ItemStack itemstack = RecipesCoffeeGrinder.smelting().getSmeltingResult(inv[0].getItem().itemID);
    	
    	if (itemstack == null) {
    		return false;
    	}
    	
    	if (inv[2] == null) {
    		return true;
    	}
    	
    	if (!inv[2].isItemEqual(itemstack)) {
    		return false;
    	}
    	
    	if (inv[2].stackSize < getInventoryStackLimit() && inv[2].stackSize < inv[2].getMaxStackSize()) {
    		return true;
    	}
    	
    	return inv[2].stackSize < itemstack.getMaxStackSize();
    }
    
    public void smeltItem() {
    	if (this.canSmelt()) {
    		ItemStack var1 = RecipesCoffeeGrinder.smelting().getSmeltingResult(this.inv[0].getItem().itemID);
    		if (this.inv[2] == null) {
    			this.inv[2] = var1.copy();
    		}
    		else if (this.inv[2].itemID == var1.itemID) {
    			++this.inv[2].stackSize;
    		}
    		--this.inv[0].stackSize;
    		if (this.inv[0].stackSize == 0) {
    			Item var2 = this.inv[0].getItem().getContainerItem();
    			this.inv[0] = var2 == null ? null : new ItemStack(var2);
    		}
    	}
    }
    
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
             return getItemBurnTime(par0ItemStack) > 0;
    }
    
    public static int getItemBurnTime(ItemStack par1ItemStack)
    {
             if (par1ItemStack == null)
             {
                     return 0;
             }

             int i = par1ItemStack.getItem().itemID;

             if (i < 256 && Block.blocksList[i].blockMaterial == Material.wood)
             {
                     return 300;
             }

             if (i == Item.stick.itemID)
             {
                     return 100;
             }

             if (i == Item.coal.itemID)
             {
                     return 1600;
             }

             if (i == Item.bucketLava.itemID)
             {
                     return 20000;
             }

             if (i == Block.sapling.blockID)
             {
                     return 100;
             }

             if (i == Item.blazeRod.itemID)
             {
                     return 2400;
             }
             if (i == Block.dirt.blockID)
             {
                     return 200;
             }
             else
             {
                     return ModLoader.addAllFuel(par1ItemStack.itemID, par1ItemStack.getItemDamage());
             }
    }
    
    public boolean isActive() {
    	return this.isActive;
    }
    
}
