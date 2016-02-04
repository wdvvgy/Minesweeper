package skuniv_kjk_minesweeper;
import javax.swing.JOptionPane;

public class Thread_Time extends Thread {
	private Minesweeper m;
	private Map map;
	private Audiofiles af;
	
	Thread_Time(Minesweeper m_, Map map_, Audiofiles af_){
		m = m_;
		m.getCount();
		map = map_;
		af = af_;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(!m.getStarted() || map.getIsend()){
				break;
			}
			
			if(!m.getCh_mode()){
				m.incCount();
				m.setTm();
			}
			
			else{
				if(m.getCount() <= 0){
					af.playMine();
					JOptionPane.showMessageDialog(null, "시간초과! 실패!");
					break;
				}
				
				m.decCount();
				m.setTm();
			}
		}
	}
}
