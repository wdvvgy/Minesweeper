package skuniv_kjk_minesweeper;
import java.awt.event.*;
import java.io.*;

public class ChallengeMode extends Newgame implements ActionListener {
	private Minesweeper m;
	
	private final String []levels = {"Easy.txt","Normal.txt","Hell.txt"};
	private int count;
	
	ChallengeMode(Minesweeper m_, Map map_, Btnimage img_, Audiofiles af_){
		super(m_,img_,map_,af_);
		m = m_;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(m.getCh_mode());
		if(!m.getCh_mode()){
			m.setCh_mode(true);
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(levels[m.getLevel()-1]));

				String stemp = br.readLine();
				String[] artemp = stemp.split(",");
				count = Integer.parseInt(artemp[1]);
				m.setCount(count);
				m.setCh_Count(count);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		else{
			m.setCh_mode(false);
		}
		SetNew();
		m.setTm();
	}

}
