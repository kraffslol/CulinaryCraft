package kraffs.culinarycraft;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class CoffeeCrop extends Block {

	private Icon[] field_94364_a;

	public CoffeeCrop(int id) {
		super(id, Material.plants);
		
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
		setTickRandomly(true);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}
	
	public int getRenderType() {
		return 6;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	/*@Override
	public void func_94332_a(IconRegister par1IconRegister)
	{
	         this.field_94336_cN = par1IconRegister.func_94245_a("culinarycraft:coffeecrop");
	} */
	
	@Override
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94364_a = new Icon[2];

        for (int i = 0; i < this.field_94364_a.length; ++i)
        {
            this.field_94364_a[i] = par1IconRegister.func_94245_a("culinarycraft:coffeecrop_" + i);
        }
    }
    
	@Override
    public Icon getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
        return this.field_94364_a[metadata];
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.getBlockMetadata(x, y, z) == 1) {
			return;
		}
		
		if (world.getBlockLightValue(x, y + 1, z) < 9) {
			return;
		}
		
		if (random.nextInt(isFertile(world, x, y - 1, z) ? 12 : 35) != 0) {
			return;
		}
		
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborId) {
		if (!canBlockStay(world, x, y ,z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}
	}
	
	@Override
	public boolean canBlockStay (World world, int x, int y, int z) {
		Block soil = blocksList[world.getBlockId(x, y - 1, z)];
		return (world.getFullBlockLightValue(x, y, z) >= 8 ||
				world.canBlockSeeTheSky(x, y, z)) &&
				(soil != null && soil.canSustainPlant(world, x, y - 1, z,
						ForgeDirection.UP, CulinaryCraft.coffeeBeans));
	}
	
	@Override
	public int idDropped(int metadata, Random random, int par2) {
		switch (metadata) {
		case 0:
			return CulinaryCraft.coffeeBeans.itemID;
		case 1:
			return CulinaryCraft.coffeeCherry.itemID;
		default:
			return -1;
		}
	}
	
	@Override
	public int idPicked (World world, int x, int y, int z) {
		return CulinaryCraft.coffeeBeans.itemID;
	}
	
}
