package kraffs.culinarycraft;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Cup extends Item {

	public Cup(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
		//setIconIndex(0);
		setUnlocalizedName("Cup");
	}
	
	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
		iconIndex = iconRegister.func_94245_a("culinarycraft:cup");
	}

}
