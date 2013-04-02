package kraffs.culinarycraft;

import kraffs.culinarycraft.client.gui.GuiCoffeeGrinder;
import kraffs.culinarycraft.client.gui.GuiRadio;
import kraffs.culinarycraft.inventory.ContainerCoffeeGrinder;
import kraffs.culinarycraft.tileentity.TileEntityCoffeeGrinder;
import kraffs.culinarycraft.tileentity.TileEntityRadio;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null)
        {
        	if(ID == 0) {
        		return new GuiCoffeeGrinder(player.inventory, (TileEntityCoffeeGrinder) te);
        	} 
    		else if (ID == 1) {
    			TileEntityRadio ter = (TileEntityRadio) world.getBlockTileEntity(x, y, z);
    			return new GuiRadio(ter);
    		}
        	else {
        		return null;
        	}
                    //return GUIChest.GUI.buildGUI(IronChestType.values()[ID], player.inventory, (TileEntityIronChest) te);
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int X, int Y, int Z)
    {
        TileEntity te = world.getBlockTileEntity(X, Y, Z);
        if (te != null && te instanceof TileEntityCoffeeGrinder && ID == 0)
        {
            TileEntityCoffeeGrinder icte = (TileEntityCoffeeGrinder) te;
            return new ContainerCoffeeGrinder(player.inventory, icte);
        }
        else if (ID == 1) {
			TileEntityRadio ter = (TileEntityRadio) world.getBlockTileEntity(X, Y, Z);
			return new GuiRadio(ter);
        }
        else
        {
            return null;
        }
    }

}
