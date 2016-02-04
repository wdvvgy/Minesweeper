package skuniv_kjk_minesweeper;
import java.awt.event.*;

public class Newgame implements ActionListener {
	private Minesweeper m;
	private Map map;
	private Btnimage img;
	private Audiofiles af;
	
	public Newgame(Minesweeper m_, Btnimage img_, Map map_, Audiofiles af_){
		m = m_;
		img = img_;
		map = map_;
		af = af_;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		SetNew();
	}
	
	public void SetNew(){
		af.playNew();
		map.setM(m.getLine(), m.getMine());
		map.SetMap();	
		m.Init();
		img.repaint();
	}
}
