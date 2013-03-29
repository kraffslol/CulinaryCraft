package kraffs.culinarycraft.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class RecipesCoffeeGrinder {
	private static final RecipesCoffeeGrinder coffeeGrinderBase = new RecipesCoffeeGrinder();
	
	private Map coffeeGrinderList = new HashMap();
	private Map coffeeGrinderExperience = new HashMap();

	public static final RecipesCoffeeGrinder smelting() {
		return coffeeGrinderBase;
	}
	
	private RecipesCoffeeGrinder()
	{
		//addSmelting(Block.dirt.blockID, new ItemStack(Block.cobblestone, 1, 0), 0.7F);
		//addSmelting(9206, new ItemStack(Block.cobblestone, 1, 0), 0.7F);
	}
	
	public void addSmelting(int id, ItemStack itemStack, float experience) {
		coffeeGrinderList.put(Integer.valueOf(id), itemStack);
		this.coffeeGrinderExperience.put(Integer.valueOf(itemStack.itemID), Float.valueOf(experience));
	}

	public ItemStack getSmeltingResult(int id) {
		return (ItemStack)coffeeGrinderList.get(Integer.valueOf(id));
	}
	
	public Map getSmeltingList() {
		return coffeeGrinderList;
	}
	
	public float getExperience(int par1) {
		return this.coffeeGrinderExperience.containsKey(Integer.valueOf(par1)) ?
		((Float)this.coffeeGrinderExperience.get(Integer.valueOf(par1))).floatValue() : 0.0F;
	}
	
}
