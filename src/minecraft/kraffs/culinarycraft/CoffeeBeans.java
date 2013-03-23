package kraffs.culinarycraft;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSeeds;

public class CoffeeBeans extends ItemSeeds {

	public CoffeeBeans(int par1, int par2, int par3) {
		super(par1, par2, par3);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("GreenCoffeeSeed");
	}
	
	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
		iconIndex = iconRegister.func_94245_a("culinarycraft:rawcoffee");
	}

}
