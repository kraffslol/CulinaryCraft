package kraffs.culinarycraft.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import kraffs.culinarycraft.tileentity.TileEntityRadio;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player playerEntity) {
		System.out.println("Got packet");
		if(packet.channel.equals("ccraft")){
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (side == Side.SERVER){
				PacketDispatcher.sendPacketToAllPlayers(packet);
			}
			DataInputStream is = new DataInputStream(new ByteArrayInputStream(packet.data));
			try{
				int type = is.readInt();
				int x = is.readInt();
				int y = is.readInt();
				int z = is.readInt();
				TileEntity te = null;
				if(side == Side.SERVER){
					EntityPlayerMP p = (EntityPlayerMP)playerEntity;
					te = MinecraftServer.getServer().worldServerForDimension(p.dimension).getBlockTileEntity(x, y, z);
				}
				if(side == Side.CLIENT){
					 te = Minecraft.getMinecraft().theWorld.getBlockTileEntity(x, y, z);
				}
				if(te instanceof TileEntityRadio){
					TileEntityRadio r = (TileEntityRadio)te;
					String surl = is.readUTF();
					r.streamURL = surl;
					boolean playing = is.readBoolean();
					if(playing && !r.isPlaying()){
						r.startStream();
					}else{
						r.stopStream();
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}

	
/*	private void handleGrinder(Packet250CustomPayload packet) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		
	}*/
	public static Packet250CustomPayload setPacket(int x, int y, int z, String streamURL, boolean playing){
		Packet250CustomPayload p = new Packet250CustomPayload();
		p.channel = "ccraft";
		ByteArrayOutputStream baos = new ByteArrayOutputStream(8);
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(0x01);//0x01 will identify this as setting the stream
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeUTF(streamURL);
			dos.writeBoolean(playing);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.length = baos.toByteArray().length;
		p.data = baos.toByteArray();
		return p;
	}
}
