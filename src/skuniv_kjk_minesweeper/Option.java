package skuniv_kjk_minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Option extends JDialog implements ActionListener {
	private Minesweeper ms;
	private Audiofiles af;
	private Map m;
	private Btnimage imgs;
	
	private JRadioButton easy;
	private JRadioButton normal;
	private JRadioButton hell;
	private ButtonGroup gr;
	private JButton ok;
	private JLabel lb;
	private Container con;
	private ImageIcon quest;
	private JButton bt;
	private JCheckBox cb;
	
	Option(Minesweeper ms_, Audiofiles af_, Map map_, Btnimage imgs_){
		ms = ms_;
		af = af_;
		imgs = imgs_;
		m = map_;
	
		setTitle("Option");
		setModal(true);
		
		con = getContentPane();
		
		JPanel thrPanel = new JPanel();
		quest = new ImageIcon("C:\\Users\\user\\workspace\\Minesweeper\\question.jpg");
		
		bt = new JButton("",quest);
		bt.setContentAreaFilled(false);
		bt.setMinimumSize(new Dimension(30,30));
		bt.setSize(new Dimension(30,30));
		bt.setMargin(new Insets(0,0,0,0));
		bt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JDialog f = new JDialog();
				
				JTextArea ta = new JTextArea();
				ta.setText("게임 난이도와 사운드 On/Off 등을 설정할수 있습니다.\n\n"
						+ "Level 의 Easy 는 8 X 8 크기의 맵과 10개의 지뢰\n\n"
						+ "Normal 는 11 X 11 크기의 맵과 18개의 지뢰\n\n"
						+ "Hell 는 15 X 15 크기의 맵과 30개의 지뢰로\n\n"
						+ "구성되어있으며 이 중 원하시는 난이도를 선택하시면 됩니다.\n\n"
						+ "또한 사운드가 시끄럽다고 느끼시면 Audio On/Off 의\n\n"
						+ "Checkbox 를 해제시켜주시면됩니다.");
				ta.setEditable(false);
				
				f.setModal(true);
				f.add(ta);
				f.setPreferredSize(new Dimension(350,250));
				f.pack();
				f.setVisible(true);
			}
			
		});
		
		lb = new JLabel("O P T I O N");
		lb.setHorizontalAlignment(JLabel.CENTER);
		
		cb = new JCheckBox("Audio On/Off");
		cb.setSelected(true);
		cb.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					//cb.setSelected(true);
					af.setAudio_right();
				}
				else if(arg0.getStateChange() == ItemEvent.DESELECTED){
					//cb.setSelected(false);
					af.setAudio_null();
				}
			}
			
		});
		
		thrPanel.add(bt);
		thrPanel.add(lb);
		
		easy = new JRadioButton("Easy (8 X 8) 지뢰 10개");
		normal = new JRadioButton("Normal (11 X 11) 지뢰 18개");
		hell = new JRadioButton("Hell (15 X 15) 지뢰 30개");
		easy.setSelected(true);
		gr = new ButtonGroup();
		gr.add(easy);
		gr.add(normal);
		gr.add(hell);
		
		ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(50,25));
		ok.addActionListener(new Ok());
		
		JPanel seconPanel = new JPanel();
		seconPanel.add(easy);
		seconPanel.add(normal);
		seconPanel.add(hell);
		seconPanel.add(cb);
		TitledBorder tb = new TitledBorder("Level");
		seconPanel.setBorder(tb);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(BorderLayout.NORTH,thrPanel);
		mainPanel.add(seconPanel);
		mainPanel.add(BorderLayout.SOUTH,ok);
		
		con.add(mainPanel);
		
		setPreferredSize(new Dimension(250,250));
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setLocation(screenSize.width/2-125, screenSize.height/2-63);
		pack();
		
	}
	
	public class Ok implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
			
			if(cb.isSelected())
				af.playNew();
			
			if(ms.getCh_mode()){
				ms.setCh_mode(false);		
			}
			
			if(easy.isSelected()){
				ms.setLevel(1);
				m.setM(8,10);			
			}
			else if(normal.isSelected()){
				ms.setLevel(2);
				m.setM(11, 18);
			}
			else if(hell.isSelected()){
				ms.setLevel(3);
				m.setM(15, 30);		
			}
			m.SetMap();
			ms.Init();

			imgs.setPanel();
			imgs.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(ms.getLevel()){
		case 1:{
			easy.setSelected(true);	
			normal.setSelected(false);
			hell.setSelected(false);
			break;
		}
		case 2:{
			easy.setSelected(false);
			normal.setSelected(true);
			hell.setSelected(false);	
			break;
		}
		case 3:{
			easy.setSelected(false);
			normal.setSelected(false);
			hell.setSelected(true);		
			break;
		}
		}
		setVisible(true);
	}
}
