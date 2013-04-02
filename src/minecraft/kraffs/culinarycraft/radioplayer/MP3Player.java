package kraffs.culinarycraft.radioplayer;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class MP3Player extends PlaybackListener implements Runnable {
	
	private String streamURL;
	private AdvancedPlayer player;
	private Thread pThread;
	private boolean isPlaying = false;

	public MP3Player(String mp3url) {
		try {
			streamURL = mp3url;
			pThread = new Thread(this);
			pThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			//playStream();
			player = new AdvancedPlayer(new URL(streamURL).openStream());
			player.setPlayBackListener(this);
			player.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	private void playStream() throws JavaLayerException {
		try {
			isPlaying = true;
			player = new AdvancedPlayer(new URL(streamURL).openStream());
			player.setPlayBackListener(this);
			player.play();
			System.out.println("Opening " + streamURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void stop() {
		if(player != null && isPlaying()) {
			player.stop();
			//isPlaying = false;
		}
	}
	
	public void playbackStarted(PlaybackEvent evt) {
		//System.out.println("Started playing");
	}
	
	public void playbackFinished(PlaybackEvent evt) {
		//System.out.println("Stopped playing");
	}
	
	public boolean isPlaying() {
		return pThread.isAlive();
	}
	
	public void setVolume(float f) {
		if(player != null) {
			player.setVolume(f);
		}
	}
	
	public float getVolume() {
		return player.getVolume();
	}

}
