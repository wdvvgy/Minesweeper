package skuniv_kjk_minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Minesweeper extends JFrame {
	
	private Map maps;
	private Btnimage imgs;
	private Audiofiles af;
	private People_Register rg;
	
	private Image Empty_icon;
	private Image []icons;
	private ImageIcon []ticons;
	private JMenuBar menubar;
	private JMenu game;
	private JMenu rank;
	private JMenu help;
	
	private JPanel bottomPanel;
	private JPanel timePanel;
	private JPanel minePanel;
	
	private JMenuItem newgame;
	private JMenuItem option;
	private JCheckBoxMenuItem challenge;
	private JMenuItem jrank;
	private JMenuItem rank_clean;
	private JMenuItem exit;
	private JMenuItem seehelp;
	private JSeparator sp;
	
	private JLabel Minecount;
	private JLabel Time;
	private JLabel TimeImage;
	private JLabel TimeImage2;
	private JLabel TimeImage3;
	private JLabel MineImage;
	private JLabel MineImage2;
	
	private int mine;
	private int count;
	private int ch_count;
	private int line;
	private int level;
	private boolean started;
	private boolean ch_mode;
	private Toolkit tk;
	
	Minesweeper(){
		setLevel(1);
		
		af = new Audiofiles();
		maps = new Map(line, mine);
		rg = new People_Register(this);
		imgs = new Btnimage(this,maps,af,rg);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Minesweeper");
		
		tk = getToolkit();
		Image main = tk.getImage("main.jpg");
		setIconImage(main);

		Loadimgs();
		Setmenu();
		SetbottomPanel();
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(BorderLayout.SOUTH,bottomPanel);
		mainPanel.add(BorderLayout.CENTER,imgs);
		
		Container con = getContentPane();
		con.add(mainPanel);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setLocation(screenSize.width/2-(imgs.getCwidth()*maps.getThis_line()/2),screenSize.height/2-(imgs.getCheight()*maps.getThis_line()/2));
		pack();
		setVisible(true);
		
	}
	
	public int getLine(){
		return line;
	}
	
	public int getMine(){
		return mine;
	}
	
	public void SetbottomPanel(){
		
		timePanel = new JPanel(new BorderLayout());
		minePanel = new JPanel(new BorderLayout());
		
		Minecount = new JLabel();
		Minecount.setText("[ Mine ]");
		Minecount.setHorizontalAlignment(JLabel.CENTER);
		
		MineImage = new JLabel(ticons[0]);
		MineImage2 = new JLabel(ticons[0]);
		
		JPanel mp = new JPanel();
		mp.add(MineImage);
		mp.add(MineImage2);
		
		minePanel.add(BorderLayout.CENTER,Minecount);
		minePanel.add(BorderLayout.SOUTH,mp);
		
		Time = new JLabel();
		Time.setText("[ Time ]");
		Time.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel tp = new JPanel();	
		TimeImage = new JLabel(ticons[0]);
		TimeImage2 = new JLabel(ticons[0]);
		TimeImage3 = new JLabel(ticons[0]);
		
		tp.add(TimeImage);
		tp.add(TimeImage2);
		tp.add(TimeImage3);
		
		timePanel.add(BorderLayout.CENTER,Time);
		timePanel.add(BorderLayout.SOUTH,tp);
		
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(minePanel, BorderLayout.WEST);
		bottomPanel.add(timePanel, BorderLayout.EAST);
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setMc();
	}
	
	public void Setmenu(){
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		game = new JMenu("Game");
		menubar.add(game);
		rank = new JMenu("Rank");
		menubar.add(rank);
		help = new JMenu("Help");
		menubar.add(help);
		
		newgame = new JMenuItem("Newgame");
		newgame.addActionListener(new Newgame(this,imgs,maps,af));
		challenge = new JCheckBoxMenuItem("Challenge");
		challenge.addActionListener(new ChallengeMode(this,maps,imgs,af));
		challenge.setSelected(false);
		option = new JMenuItem("Option");
		option.addActionListener(new Option(this, af,maps,imgs));
		jrank = new JMenuItem("Rank");
		jrank.addActionListener(new Rank(this,rg));
		rank_clean = new JMenuItem("Rank Reset");
		rank_clean.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rg.setClean();
			}

		});
	
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		sp = new JSeparator();
		seehelp = new JMenuItem("See Help");
		seehelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame hf = new JFrame("Help");
				hf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JTextArea ta = new JTextArea();
				ta.append("서경대 컴퓨터과학과 2학년 김정기가 만든 지뢰찾기입니다!\n\n"
						+ "게임방식은 윈도우즈에 탑재되어있는 지뢰찾기와 동일합니다.\n\n"
						+ "Easy(초급),Normal(중급),Hell(고급)의 난이도로\n\n"
						+ "구성되어 있으며 각 8X8(지뢰10개), 11X11(지뢰18개),\n\n"
						+ "15X15(지뢰30개) 의 배치를 가지고 있습니다.\n\n"
						+ "지뢰를 모두 찾으면 게임에 승리하게 됩니다.\n\n"
						+ "Option 을 이용해 난이도 조절과 효과음 On/Off 설정\n\n"
						+ "등을 할 수 있습니다.\n\n"
						+ "Challenge 는 각 난이도별 1등의 시간점수를 기준으로\n\n"
						+ "시작해 0초가 되면 게임이 종료됩니다.\n\n"
						+ "빠른시간안에 1등을 노려보고싶으시면 도전하세요.");
				ta.setEditable(false);
				
				Container con = hf.getContentPane();
				con.add(ta);
				
				Toolkit tk = Toolkit.getDefaultToolkit();
				Dimension screenSize = tk.getScreenSize();
				hf.setLocation(screenSize.width/2-150, screenSize.height/2-100);
				hf.setResizable(false);
				hf.pack();
				hf.setVisible(true); 
			}
		});
		
		game.addMenuListener(new MenuListener(){
			
			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				if(rg.get_Peoples(level).size() == 0){
					challenge.setEnabled(false);
				}
				else
					challenge.setEnabled(true);
				
				if(challenge.isSelected())
					option.setEnabled(false);
				else
					option.setEnabled(true);
			}
			
		});

		game.add(newgame);
		game.add(challenge);
		game.add(option);
		game.add(sp);
		game.add(exit);
		rank.add(jrank);
		rank.add(rank_clean);
		help.add(seehelp);
	}

	public void Loadimgs(){
		
		icons = new Image[10];
		icons[0] = tk.getImage("empty.jpg");
		icons[1] = tk.getImage("1.jpg");
		icons[2] = tk.getImage("2.jpg");
		icons[3] = tk.getImage("3.jpg");
		icons[4] = tk.getImage("4.jpg");
		icons[5] = tk.getImage("5.jpg");
		icons[6] = tk.getImage("6.jpg");
		icons[7] = tk.getImage("mine.jpg");
		icons[8] = tk.getImage("flag.jpg");
		icons[9] = tk.getImage("none.jpg");
		
		ticons = new ImageIcon[10];
		ticons[0] = new ImageIcon("t0.jpg");
		ticons[1] = new ImageIcon("t1.jpg");
		ticons[2] = new ImageIcon("t2.jpg");
		ticons[3] = new ImageIcon("t3.jpg");
		ticons[4] = new ImageIcon("t4.jpg");
		ticons[5] = new ImageIcon("t5.jpg");
		ticons[6] = new ImageIcon("t6.jpg");
		ticons[7] = new ImageIcon("t7.jpg");
		ticons[8] = new ImageIcon("t8.jpg");
		ticons[9] = new ImageIcon("t9.jpg");	
	}
	
	
	public void setMc(){
		MineImage2.setIcon(ticons[(maps.getMine()%10)]);
		MineImage.setIcon(ticons[(maps.getMine()/10)]);
	}
	
	public void setTm(){
		TimeImage3.setIcon(ticons[(count%10)]);
		TimeImage2.setIcon(ticons[(count/10%10)]);
		TimeImage.setIcon(ticons[(count/100)]);
	}

	
	public void Init(){
		if(!ch_mode)
			setCount(0);
		else{
			setCount(ch_count);
		}
		setStarted(false);
		setTm();
		setMc();
	}
	
	public Image getEmpty(){ return Empty_icon; }
	
	public Image getIcon(int i){ 
		if(i < 0)
			return icons[7];
		return icons[i]; 
	}
	
	public boolean getStarted(){
		return started;
	}
	
	public void setStarted(boolean temp){
		started = temp;
	}
	
	public int getCount(){
		return count;
	}
	
	public void incCount(){
		count++;
	}
	
	public void decCount(){
		count--;
	}
	
	public void setCount(int temp){	
		count = temp;
	}
	
	public void setCh_Count(int temp){
		ch_count = temp;
	}
	
	public int getchCount(){
		return ch_count;
	}
	
	public void setLevel(int level_){
		level = level_;
		switch(level){
		case 1:{
			line = 8;
			mine = 10;
			break;
		}
		case 2:{
			line = 11;
			mine = 18;
			break;
		}		
		case 3:{
			line = 15;
			mine = 30;
			break;
		}
		}
	}
	
	public int getLevel(){
		return level;
	}
	
	public boolean getCh_mode(){
		return ch_mode;
	}
	
	public void setCh_mode(boolean temp){
		ch_mode = temp;
	}

}