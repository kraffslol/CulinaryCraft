package kraffs.culinarycraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CoffeePowder extends Item {

	public CoffeePowder(int id) {
		super(id);
		// TODO Auto-generated constructor stub
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("CoffeePowder");
	}
	
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
		iconIndex = iconRegister.registerIcon("culinarycraft:coffeepowder");
	}

}
