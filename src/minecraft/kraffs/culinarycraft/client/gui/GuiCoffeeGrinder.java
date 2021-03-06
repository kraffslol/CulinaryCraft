package kraffs.culinarycraft.client.gui;

import kraffs.culinarycraft.inventory.ContainerCoffeeGrinder;
import kraffs.culinarycraft.tileentity.TileEntityCoffeeGrinder;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiCoffeeGrinder extends GuiContainer {

	private TileEntityCoffeeGrinder coffeegrinderInventory;
	
	public GuiCoffeeGrinder(InventoryPlayer inventoryPlayer, TileEntityCoffeeGrinder tileEntity) {
		super(new ContainerCoffeeGrinder(inventoryPlayer, tileEntity));
		coffeegrinderInventory = tileEntity;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		int texture = mc.renderEngine.getTexture("/mods/culinarycraft/textures/gui/coffeegrinder.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//mc.renderEngine.func_98187_b("/mods/culinarycraft/textures/blocks/coffeegrinder.png");
		mc.renderEngine.bindTexture("/mods/culinarycraft/textures/gui/coffeegrinder.png");
		//this.mc.renderEngine.bindTexture(texture);
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/trap.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		//drawGradientRect(20, 20, width - 20,  height - 20, 0x60bb0000, 0xa0770055);
		//super.drawScreen(par3, par2, par1);
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		if (coffeegrinderInventory.isBurning()) {
			int burn = coffeegrinderInventory.getBurnTimeRemainingScaled(14);
			drawTexturedModalRect(x + 73, y + 59, 176, 16, burn, 10);
		}
		
		int update = coffeegrinderInventory.getCookProgressScaled(16);
		drawTexturedModalRect(x + 89, y + 55, 191, 15, -update, -update);
		
	}

}
