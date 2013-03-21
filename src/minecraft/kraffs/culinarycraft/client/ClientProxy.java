package kraffs.culinarycraft.client;

import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import kraffs.culinarycraft.CommonProxy;
import kraffs.culinarycraft.GuiCoffeeGrinder;
import kraffs.culinarycraft.TileEntityCoffeeGrinder;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		//MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		//MinecraftForgeClient.preloadTexture(BLOCK_PNG);
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{        
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof TileEntityCoffeeGrinder)
        {
        	return new GuiCoffeeGrinder(player.inventory, (TileEntityCoffeeGrinder) te);
            //return GUIChest.GUI.buildGUI(IronChestType.values()[ID], player.inventory, (TileEntityIronChest) te);
        }
        else
        {
            return null;
        }
	}
	
}
