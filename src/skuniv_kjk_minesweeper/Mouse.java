package skuniv_kjk_minesweeper;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Mouse  implements MouseListener {
	private Minesweeper m;
	private Map map;
	private Btnimage imgs;
	private Audiofiles af;
	private People_Register rg;
	private Thread_Time t;
	
	Mouse(Minesweeper m_, Map map_, Btnimage imgs_, Audiofiles af_, People_Register rg_) {
		m = m_;
		map = map_;
		imgs = imgs_;
		af = af_;
		rg = rg_;
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		if(map.getIsend())
			return;
		
		// TODO Auto-generated method stub
		int x =(int) arg0.getX() / (imgs.getWidth()/map.getThis_line());
		int y =(int) arg0.getY() / (imgs.getHeight()/map.getThis_line());

		if(arg0.getClickCount() == 2){
			map.Check2(y, x);
			
			if(!map.getIsend()){
				af.playClick();
			}
			else{
				map.setIsend(true);
				m.setStarted(false);
				af.playMine();
				JOptionPane.showMessageDialog(null, "지뢰를 밟았습니다...");		
				
			}
			imgs.repaint();
			return;
		}
		
		switch(arg0.getButton()){
		
		case MouseEvent.BUTTON1:{
			if(!m.getStarted()){
				if(!m.getCh_mode())
					m.setCount(0);
				m.setStarted(true);	
				t = new Thread_Time(m,map,af);
				t.start();
			}
			
			map.Check(y, x);
			
			if(!map.getIsend()){
				af.playClick();
			}
			
			else{
				map.setIsend(true);
				m.setStarted(false);
				af.playMine();
				JOptionPane.showMessageDialog(null, "지뢰를 밟았습니다...");		
				break;
			}
			break;
		}
		
		case MouseEvent.BUTTON3:{
			
			af.playFlag();
			map.Flag_check(y, x);
			m.setMc();
			if(map.getMine() == 0){
				if(map.AllCheck()){
					m.setStarted(false);
					af.playWin();			
					imgs.repaint();
					
					if(m.getCh_mode()){
						m.setCount(m.getchCount()-m.getCount());
					}
					
					JOptionPane.showMessageDialog(null, "승리!! 기록 : " + m.getCount());
					rg.setVisible(true);
					break;
				}
				map.Setcheck();
			}			
		}
		
		default:
			break;
		}
		imgs.repaint();
	}
}
