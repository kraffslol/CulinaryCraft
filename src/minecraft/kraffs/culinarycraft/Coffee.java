package kraffs.culinarycraft;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class Coffee extends ItemFood {

	public Coffee(int par1, int par2, float par3, boolean par4) {
		super(par1, par2, par3, par4);
		
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabFood);
		//setIconIndex(0);
		setUnlocalizedName("Coffee");
	}
	
	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
		iconIndex = iconRegister.func_94245_a("culinarycraft:coffee");
	}

}
