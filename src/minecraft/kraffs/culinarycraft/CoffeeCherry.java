package kraffs.culinarycraft;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CoffeeCherry extends Item {

	public CoffeeCherry(int id) {
		super(id);
		// TODO Auto-generated constructor stub
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
		iconIndex = iconRegister.func_94245_a("culinarycraft:coffeecherry");
	}
	
}
