package skuniv_kjk_minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Rank extends JDialog implements ActionListener {
	private Minesweeper m;
	private People_Register rg;
	private JLabel lb;
	private JTextArea list;
	private JTextArea names;
	private JTextArea counts;
	private JList<String> list_level;
	private Container con;
	
	Rank(Minesweeper m_, People_Register rg_){
		rg = rg_;
		m = m_;
		
		String[] levels = new String[]{"Easy","Normal","Hell"};
		list_level = new JList<String>(levels);
		list_level.setPreferredSize(new Dimension(60,60));
		list_level.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				
				switch(list_level.getSelectedIndex()){
				case 0:{
					printRank(1);
					break;
				}
				case 1:{
					printRank(2);
					break;
				}
				case 2:{
					printRank(3);
					break;
				}
				}
			}
			
		});
		
		list = new JTextArea();
		list.setEditable(false);
		list.setText("1등\n2등\n3등\n4등\n5등");
		
		names = new JTextArea();
		names.setEditable(false);
		
		counts = new JTextArea();
		counts.setEditable(false);
		
		lb = new JLabel("",SwingConstants.LEFT);
		lb.setText("난이도별로 조회가 가능합니다.");
		
		JPanel sp = new JPanel();
		sp.add(lb);
		
		JPanel pn = new JPanel(new GridLayout(1,4));
		pn.add(list_level);
		pn.add(list);
		pn.add(names);
		pn.add(counts);
		sp.add(pn);
		
		con = getContentPane();
		con.add(sp);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setPreferredSize(new Dimension(270,150));
		setLocation(screenSize.width/2-m.getWidth()/2,screenSize.height/2-m.getHeight()/2);
		setTitle("Rank");
		setResizable(false);
		
		pack();	
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		list_level.setSelectedIndex(m.getLevel()-1);
		printRank(m.getLevel());
		setModal(true);
		setVisible(true);
	}
	
	public void printRank(int level){		
		names.setText(null);
		counts.setText(null);
		
		for(People temp : rg.get_Peoples(level)){
			String []str = temp.toString().split(",");
			names.append(str[0] + "\n");
			counts.append(str[1] + "초\n");
		}

	}
}
