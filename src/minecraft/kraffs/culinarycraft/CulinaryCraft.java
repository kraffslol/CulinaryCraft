package kraffs.culinarycraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="CulinaryCraft", name="CulinaryCraft", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class CulinaryCraft {

	@Instance("CulinaryCraft")
	public static CulinaryCraft instance;
	
	public static final Block coffeeCrop = new CoffeeCrop(2871);
	
	private final static Item friedEgg = new FriedEgg(9201, 5, 2.0F, false);
	private final static Item Cup = new Cup(9202);
	private final static Item Coffee = new Coffee(9203, 1, 2.0F, false).setPotionEffect(1, 5, 1, 1.0f);
	public final static ItemSeeds coffeeBeans = (ItemSeeds) new CoffeeBeans(9204, coffeeCrop.blockID, Block.tilledField.blockID);
	public final static Item coffeeCherry = new CoffeeCherry(9205); // Coffee Cherry
	
	// Test
	
	
	@SidedProxy(clientSide="kraffs.culinarycraft.client.ClientProxy", serverSide="kraffs.culinarycraft.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		addLang();
		
		MinecraftForge.addGrassSeed(new ItemStack(coffeeBeans), 10);
		
		//ItemStack eggStack = new ItemStack(Item.egg);
		//ItemStack friedeggStack = new ItemStack(friedEgg);
		addRecipes();

		
		
		//testFood = new ItemFood(friedeggStack.itemID, 0, 3);
		
		registerBlocks();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
	public void registerBlocks() {
		GameRegistry.registerBlock(coffeeCrop, "coffeeCrop");
	}
	
	public void addLang() {
		// More to come
		LanguageRegistry.addName(friedEgg, "Fried Egg");
		LanguageRegistry.addName(Cup, "Empty Cup");
		LanguageRegistry.addName(Coffee, "Coffee");
		LanguageRegistry.addName(coffeeCherry, "Coffee Cherry");
		LanguageRegistry.addName(coffeeBeans, "Green Coffee Seed");
	}
	
	public void addRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(coffeeBeans, 4),  new ItemStack(coffeeCherry));
		GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(friedEgg), 0.1f);
	}
	
}
