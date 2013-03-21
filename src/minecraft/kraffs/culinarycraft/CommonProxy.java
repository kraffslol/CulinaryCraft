package kraffs.culinarycraft;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {
	//public static String ITEMS_PNG = "/kraffs/culinarycraft/items.png";	
	//public static String BLOCK_PNG = "/kraffs/culinarycraft/block.png";
	
	public void registerRenderers() {
		
	}
	
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int X, int Y, int Z)
    {
        TileEntity te = world.getBlockTileEntity(X, Y, Z);
        if (te != null && te instanceof TileEntityCoffeeGrinder)
        {
            TileEntityCoffeeGrinder icte = (TileEntityCoffeeGrinder) te;
            return new ContainerCoffeeGrinder(player.inventory, icte);
        }
        else
        {
            return null;
        }
    }
    
}
