package kraffs.culinarycraft.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kraffs.culinarycraft.CulinaryCraft;
import kraffs.culinarycraft.network.PacketHandler;
import kraffs.culinarycraft.radioplayer.MP3Player;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntityRadio extends TileEntity {
	
	private MP3Player player = null;
	private boolean isPlaying = false;
	public String streamURL = "http://sverigesradio.se/topsy/direkt/164-hi-mp3.pls";
	
	public Block getBlockType() {
		return CulinaryCraft.radio;
	}

	//@SideOnly(Side.CLIENT)
	public void startStream() {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(!isPlaying) {
			isPlaying = true;
			//player = new MP3Player(streamURL);
			if(side == Side.CLIENT) {
				player = new MP3Player(streamURL);
				CulinaryCraft.playerList.add(player);
			}
		} else {
			//System.err.println("Tried to play a stream twice out of one radio!");
		}
	}

	//@SideOnly(Side.CLIENT)
	public void stopStream() {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(isPlaying) {
			//player.stop();
			if(side == Side.CLIENT) {
				player.stop();
				CulinaryCraft.playerList.remove(player);
			}
			isPlaying = false;
		} else {
			//System.err.println("Tried to stop a nonplaying radio");
		}
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void invalidate(){
		/*if(isPlaying){
			stopStream();
		} */
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(PacketHandler.setPacket(xCoord, yCoord, zCoord, streamURL, !isPlaying()));
		super.invalidate();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void onChunkUnload(){
		if(isPlaying){
			stopStream();
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateEntity() {
		if(Minecraft.getMinecraft().thePlayer != null && player != null && !isInvalid()){
			float vol = (float)getDistanceFrom(Minecraft.getMinecraft().thePlayer.posX,Minecraft.getMinecraft().thePlayer.posY,Minecraft.getMinecraft().thePlayer.posZ);
			if(vol > 10000){
				player.setVolume(0f);
			}else{
				float v2 = (10000f / vol) / 100f;
				if(v2 > 1){
					player.setVolume(1);
				}else{
					player.setVolume(v2);
				}
			}
			//System.out.println("streamurl: \""+streamURL+"\"");
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readFromNBT(par1NBTTagCompound);
		//TODO: read last used stream
		//streamURL = par1NBTTagCompound.getString("streamurl");
		System.out.println("Read: \""+streamURL+"\"");
		streamURL = par1NBTTagCompound.getString("streamurl");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeToNBT(par1NBTTagCompound);
		//TODO: write last used stream
		par1NBTTagCompound.setString("streamurl", streamURL);
		System.out.println("Wrote: \""+streamURL+"\"");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return PacketHandler.setPacket(xCoord, yCoord, zCoord, streamURL, isPlaying);
	}
	
}
