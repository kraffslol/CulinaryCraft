package kraffs.culinarycraft;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CoffeeGrinder extends BlockContainer {

	public CoffeeGrinder(int id) {
		super(id, Material.iron);
		setUnlocalizedName("CoffeeGrinder");
		setHardness(3.0F);
		//setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		
		setCreativeTab(CreativeTabs.tabMisc);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
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
		
		player.openGui(CulinaryCraft.instance, 0, world, x, y, z);
		return true;
	}
	
    @Override
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
        world.markBlockForUpdate(i, j, k);
    }
    
    
    private void setDefaultDirection(World par1World, int x, int y, int z)
    {
             TileEntity blockEntity = par1World.getBlockTileEntity(x, y, z);
             if (par1World.isRemote)
             {
             return;
             }

             int i = par1World.getBlockId(x, y, z - 1);
             int j = par1World.getBlockId(x, y, z + 1);
             int k = par1World.getBlockId(x - 1, y, z);
             int l = par1World.getBlockId(x + 1, y, z);
             byte byte0 = 3;
            
             if (Block.opaqueCubeLookup[i] && !Block.opaqueCubeLookup[j])
    {
    byte0 = 3;
    }
    if (Block.opaqueCubeLookup[j] && !Block.opaqueCubeLookup[i])
    {
    byte0 = 2;
    }
    if (Block.opaqueCubeLookup[k] && !Block.opaqueCubeLookup[l])
    {
    byte0 = 5;
    }
    if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[k])
    {
    byte0 = 4;
    }
    ((TileEntityCoffeeGrinder)blockEntity).setFrontDirection(byte0);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityCoffeeGrinder();
        //return new TileEntityChest();
    	//return null;
    }
    

    
//    @Override
//    public int func_94328_b_(World par1World, int par2, int par3, int par4, int par5)
//    {
//        return Container.func_94526_b((TileEntityCoffeeGrinder) par1World.getBlockTileEntity(par2, par3, par4));
//    }
	
}
