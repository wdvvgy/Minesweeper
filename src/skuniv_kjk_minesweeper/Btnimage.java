package skuniv_kjk_minesweeper;
import java.awt.*;
import javax.swing.*;

public class Btnimage extends JPanel{
	private Minesweeper mn;
    private Audiofiles af;
	private Map map;
	private int Cell_width = 22;
	private int Cell_height = 22;
	private Image temp;
	
	Btnimage(Minesweeper mn_, Map map_, Audiofiles af_,People_Register rg_){
		mn = mn_;
		map = map_;
		af = af_;
		af.playNew();

		addMouseListener(new Mouse(mn, map, this, af,rg_));	
		setPanel();
		
	}

	public void setPanel(){	
		try
		{
			setPreferredSize(new Dimension(Cell_width*map.getRow()+1, Cell_height*map.getCol()));
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			mn.pack();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void paint(Graphics g){
		Cell_width = getWidth() / map.getThis_line();
		Cell_height = getHeight() / map.getThis_line();
		g.clearRect(0, 0, getWidth(), getHeight());
		for(int i=0; i<map.getRow(); i++){
			for(int j=0; j<map.getCol(); j++){
				temp = mn.getIcon(map.getCell(i, j));
				int x = j*(Cell_width);
				int y = i*(Cell_height);
				g.drawImage(temp, x, y, Cell_width, Cell_height, this);
				g.drawRect(x,y,Cell_width,Cell_height);
			}
		}
		updateUI();
	}
	
	public int getCwidth(){
		return Cell_width; 
	}
	
	public int getCheight(){
		return Cell_height; 
	}
}
