package kraffs.culinarycraft.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import kraffs.culinarycraft.CommonProxy;
import kraffs.culinarycraft.client.gui.GuiCoffeeGrinder;
import kraffs.culinarycraft.tileentity.TileEntityCoffeeGrinder;
import kraffs.culinarycraft.tileentity.TileEntityRadio;

public class ClientProxy extends CommonProxy {
	@Override
	public void initTileEntities() {
		System.out.println("registering renderer");
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadio.class, new RenderRadioBlock());
	}
	
}
