package skuniv_kjk_minesweeper;
import java.applet.*;
import java.io.File;
import java.net.MalformedURLException;

public class Audiofiles {
	private AudioClip click;
	private AudioClip mine;
	private AudioClip flag;
	private AudioClip win;
	private AudioClip news;
	
	private File clickf;
	private File minef;
	private File flagf;
	private File winf;
	private File newf;

	Audiofiles(){
		clickf = new File("click.wav");
		minef = new File("mine.wav");
		flagf = new File("flag.wav");
		winf = new File("win.wav");
		newf = new File("new.wav");
		
		setAudio_right();
	
	}

	public void playClick(){
		try{
			click.play();
		
		}
		catch(Exception e){
			
		}
	}

	public void playMine(){
		try{
			mine.play();
		}
		catch(Exception e){

		}
	}

	public void playFlag(){
		try{
			flag.play();
		}
		catch(Exception e){

		}
	}

	public void playWin(){
		try{
			win.play();
		}
		catch(Exception e){

		}
	}

	public void playNew(){
		try{
			news.play();
		}
		catch(Exception e){

		}
	}
	
	public void setAudio_null(){
		click = null;
		mine = null;
		flag = null;
		win = null;
		news = null;
	}
	
	public void setAudio_right(){
		try {
			click = Applet.newAudioClip(clickf.toURL());
			mine = Applet.newAudioClip(minef.toURL());
			flag = Applet.newAudioClip(flagf.toURL());
			win = Applet.newAudioClip(winf.toURL());
			news = Applet.newAudioClip(newf.toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
