package kraffs.culinarycraft;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DriedCoffeeBeans extends Item {

	public DriedCoffeeBeans(int id) {
		super(id);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("Coffee Beans");
	}

	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
		iconIndex = iconRegister.func_94245_a("culinarycraft:coffeebeans");
	}
	
}
