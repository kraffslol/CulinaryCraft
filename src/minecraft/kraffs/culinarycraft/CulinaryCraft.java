package kraffs.culinarycraft;

import java.util.ArrayList;
import java.util.List;

import kraffs.culinarycraft.block.CoffeeCrop;
import kraffs.culinarycraft.block.CoffeeGrinder;
import kraffs.culinarycraft.block.Radio;
import kraffs.culinarycraft.item.Coffee;
import kraffs.culinarycraft.item.CoffeeBeans;
import kraffs.culinarycraft.item.CoffeeCherry;
import kraffs.culinarycraft.item.CoffeePowder;
import kraffs.culinarycraft.item.Cup;
import kraffs.culinarycraft.item.DriedCoffeeBeans;
import kraffs.culinarycraft.item.FriedEgg;
import kraffs.culinarycraft.network.ConnectionHandler;
import kraffs.culinarycraft.network.PacketHandler;
import kraffs.culinarycraft.radioplayer.MP3Player;
import kraffs.culinarycraft.recipe.RecipesCoffeeGrinder;
import kraffs.culinarycraft.tileentity.TileEntityCoffeeGrinder;
import kraffs.culinarycraft.tileentity.TileEntityRadio;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

@Mod(modid="CulinaryCraft", name="CulinaryCraft", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"ccraft"}, packetHandler = PacketHandler.class, connectionHandler = ConnectionHandler.class)
public class CulinaryCraft {

	@Instance("CulinaryCraft")
	public static CulinaryCraft instance;
	
	public static GuiHandler guiHandler = new GuiHandler();
	
	public static final Block coffeeCrop = new CoffeeCrop(2871);
	public static final Block coffeeGrinder = new CoffeeGrinder(2872);
	public static final Block radio = new Radio(2873, Material.wood);
	
	private final static Item friedEgg = new FriedEgg(9201, 5, 2.0F, false);
	private final static Item Cup = new Cup(9202);
	private final static Item Coffee = new Coffee(9203, 1, 2.0F, false).setPotionEffect(1, 5, 1, 1.0f);
	public final static ItemSeeds coffeeBeans = (ItemSeeds) new CoffeeBeans(9204, coffeeCrop.blockID, Block.tilledField.blockID);
	public final static Item coffeeCherry = new CoffeeCherry(9205); // Coffee Cherry
	public final static Item driedcoffeeBeans = new DriedCoffeeBeans(9206);
	public final static Item coffeePowder = new CoffeePowder(9207);
	
	public static List<MP3Player> playerList = new ArrayList<MP3Player>();
	
	// Test
	
	
	@SidedProxy(clientSide="kraffs.culinarycraft.client.ClientProxy", serverSide="kraffs.culinarycraft.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {

		
		NetworkRegistry.instance().registerGuiHandler(instance, guiHandler);
		
		addLang();
		
		addSeeds();
		
		//ItemStack eggStack = new ItemStack(Item.egg);
		//ItemStack friedeggStack = new ItemStack(friedEgg);
		addRecipes();
		RecipesCoffeeGrinder.smelting().addSmelting(driedcoffeeBeans.itemID, new ItemStack(coffeePowder, 2, 0), 0.7F);
		//ic2.api.Ic2Recipes.addMaceratorRecipe(new ItemStack(driedcoffeeBeans), new ItemStack(coffeePowder, 2, 0));
		//testFood = new ItemFood(friedeggStack.itemID, 0, 3);
		
		registerBlocks();
		
		
		GameRegistry.registerTileEntity(TileEntityCoffeeGrinder.class, "CoffeeGrinder");	
		GameRegistry.registerTileEntity(TileEntityRadio.class, "Radio");
		proxy.initTileEntities();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
	@ServerStopped
	public void serverStop(FMLServerStoppedEvent event) {
		//System.out.println("Stopped!");
		killAllStreams();
	}
	
	public static void killAllStreams() {
		for(MP3Player p : playerList) {
			p.stop();
		}
	}
	
	public void addSeeds() {
		MinecraftForge.addGrassSeed(new ItemStack(coffeeBeans), 10);
	}
	
	public void registerBlocks() {
		GameRegistry.registerBlock(coffeeCrop, "coffeeCrop");
		GameRegistry.registerBlock(coffeeGrinder, "CoffeeGrinder");
		GameRegistry.registerBlock(radio, "Radio");
	}
	
	public void addLang() {
		// More to come
		LanguageRegistry.addName(coffeeGrinder, "Coffee Grinder");
		LanguageRegistry.addName(friedEgg, "Fried Egg");
		LanguageRegistry.addName(Cup, "Empty Cup");
		LanguageRegistry.addName(Coffee, "Coffee");
		LanguageRegistry.addName(coffeeCherry, "Coffee Cherry");
		LanguageRegistry.addName(coffeeBeans, "Green Coffee Seed");
		LanguageRegistry.addName(driedcoffeeBeans, "Coffee Beans");
		LanguageRegistry.addName(coffeePowder, "Coffee Powder");
		LanguageRegistry.addName(radio, "Radio");
	}
	
	public void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(coffeeGrinder, 4, 0), new Object[]{"DD", 'D', Block.dirt});
		GameRegistry.addShapelessRecipe(new ItemStack(coffeeBeans, 4),  new ItemStack(coffeeCherry));
		GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(friedEgg), 0.1f);
		GameRegistry.addSmelting(coffeeCherry.itemID, new ItemStack(driedcoffeeBeans), 0.1f);
	}
	
}
