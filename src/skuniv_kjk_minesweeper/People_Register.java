package skuniv_kjk_minesweeper;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class People_Register extends JFrame {
	private JTextField name;
	private JLabel lb;
	private JButton ok;
	private ArrayList <People>E_peoples;
	private ArrayList <People>N_peoples;
	private ArrayList <People>H_peoples;
	
	private Minesweeper m;
	
	private BufferedWriter bw;
	private BufferedReader br;
	private String info;
	private StringBuilder rank_info;
	
	private final String []levels = {"Easy.txt","Normal.txt","Hell.txt"};
		
	People_Register(Minesweeper m_){
		m = m_;
		
		setTitle("Victory !");
		Container con = getContentPane();
		
		lb = new JLabel();
		lb.setText("이름 ");
		lb.setPreferredSize(new Dimension(30,40));
				
		name = new JTextField();
		name.setMargin(new Insets(5,5,5,5));
		name.setPreferredSize(new Dimension(100,40));
		
		ok = new JButton("OK");
		ok.addActionListener(new Ok(this));
		ok.setPreferredSize(new Dimension(60,40));
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(lb);
		mainPanel.add(name);
		mainPanel.add(ok);
		con.add(mainPanel);
		
		setPreferredSize(new Dimension(220,80));
		pack();
		setLocation(500,500);
		setResizable(false);
		setVisible(false);
		
		E_peoples = new ArrayList<People>();
		N_peoples = new ArrayList<People>();
		H_peoples = new ArrayList<People>();
		
		getExistpeople();
		name.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER ){
					setVisible(false);
				
					Regist(getName(),m.getCount(),m.getLevel());
					
					m.setCount(0);
					name.setText(null);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
			
		});
	}
	
	public void Regist(String name, int count,int level){
		People temp = new People(name,count);
		
		switch(level){
		case 1:{
			E_peoples.add(temp);
			Collections.sort(E_peoples,temp);
			setRankinfo(level, E_peoples);
			break;
		}
		case 2:{
			N_peoples.add(temp);
			Collections.sort(N_peoples,temp);
			setRankinfo(level, N_peoples);
			break;
		}
		case 3:{
			H_peoples.add(temp);
			Collections.sort(H_peoples,temp);
			setRankinfo(level, H_peoples);
			break;
		}
		}
	}
		
	public ArrayList<People> get_Peoples(int level){
		switch(level){
		case 1:{
			for(int i=5; i<E_peoples.size(); i++){
				E_peoples.remove(i);
			}
			return E_peoples;
		}
		case 2:{
			for(int i=5; i<N_peoples.size(); i++){
				N_peoples.remove(i);
			}
			return N_peoples;
		}
		case 3:{
			for(int i=5; i<H_peoples.size(); i++){
				H_peoples.remove(i);
			}
			return H_peoples;
		}
		default:{
			return null;
		}
		}
	}
	
	public String getName(){
		return name.getText();
	}
	
	public class Ok implements ActionListener{
		Ok(People_Register rf){
			rf.setVisible(false);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			setVisible(false);
			
			Regist(getName(),m.getCount(),m.getLevel());
			
			m.setCount(0);
			name.setText(null);

		}
	}
	
	public String getRankinfo(){
		return rank_info.toString();
	}
	
	public void setRankinfo(int level, ArrayList<People> temps){
		try {
			bw = new BufferedWriter(new FileWriter(levels[level-1]));
	
			for(People temp : temps){
				bw.append(temp.toString() + "\n");
			}
			
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getLevelinfo(int level){
		rank_info = new StringBuilder();

		try {
			br = new BufferedReader(new FileReader(levels[m.getLevel()-1]));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while((info = br.readLine()) != null){
				rank_info.append(info+"\n");
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getExistpeople(){
		rank_info = new StringBuilder();
		
		int count = 0;
		
		while(count < 3){
		try{
			br = new BufferedReader(new FileReader(levels[count++]));
			while((info = br.readLine()) != null){
				rank_info.append(info+"\n");
				String []str = info.toString().split(",");
				Regist(str[0], Integer.parseInt(str[1]), count);
			}
			br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
	}
	
	public void setClean(){
		
		for(int i=0; i<E_peoples.size(); i++){
			E_peoples.removeAll(E_peoples);
		}
		
		for(int i=0; i<N_peoples.size(); i++){
			N_peoples.removeAll(N_peoples);
		}
		
		for(int i=0; i<H_peoples.size(); i++){
			H_peoples.removeAll(H_peoples);
		}

		for(int i=0; i<3; i++){
			try {
				bw = new BufferedWriter(new FileWriter(levels[i]));
				bw.write("");
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}