package kraffs.culinarycraft;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CoffeeGrinder extends BlockContainer {

	public CoffeeGrinder(int id) {
		super(id, Material.iron);
		setUnlocalizedName("CoffeeGrinder");
		setHardness(3.0F);
		setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		
		setCreativeTab(CreativeTabs.tabMisc);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void func_94332_a(IconRegister par1IconRegister)
	{
		this.field_94336_cN = par1IconRegister.func_94245_a("culinarycraft:coffeegrinder");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		if (tile_entity == null || player.isSneaking()) {
			return false;
		}
		
		player.openGui(CulinaryCraft.instance, 0, world, y, y, z);
		return true;
	}
	
    @Override
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        world.markBlockForUpdate(i, j, k);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityCoffeeGrinder();
    	//return null;
    }
    
    @Override
    public boolean func_96468_q_()
    {
        return true;
    }
    
    @Override
    public int func_94328_b_(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.func_94526_b((TileEntityCoffeeGrinder) par1World.getBlockTileEntity(par2, par3, par4));
    }
    
//    @Override
//    public int func_94328_b_(World par1World, int par2, int par3, int par4, int par5)
//    {
//        return Container.func_94526_b((TileEntityCoffeeGrinder) par1World.getBlockTileEntity(par2, par3, par4));
//    }
	
}
