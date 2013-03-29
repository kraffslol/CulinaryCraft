package kraffs.culinarycraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class FriedEgg extends ItemFood {

	public FriedEgg(int par1, int par2, float par3, boolean par4) {
		super(par1, par2, par3, par4);
		
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabFood);
		//setIconIndex(0);
		setUnlocalizedName("friedEgg");
	}
	
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
		iconIndex = iconRegister.registerIcon("culinarycraft:friedegg");
	}

}
