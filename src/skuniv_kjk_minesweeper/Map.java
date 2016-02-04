package skuniv_kjk_minesweeper;

public class Map {
	private final int itsMine = -1;
	
	private int[][] map;
	private int[][] map2;
	private boolean[][] flag;
	private boolean[][] flag2;
	private boolean[][] open;
	
	private int This_line;
	private int mine;
	private int minetemp;
	private boolean isend;
	private int Checks;
	
	Map(int M_This_line, int M_mine){
		This_line = M_This_line;
		setM(M_This_line,M_mine);
		SetMap();	
	}
	
	public void SetMap(){	
		for(int i=0; i<This_line; i++){
			for(int j=0; j<This_line; j++){
				map[i][j] = 0;
				map2[i][j] = 9;
				flag[i][j] = false;
				flag2[i][j] = false;
				open[i][j] = false;
			}
		}
		
		for(int i=0; i<mine; i++){

			int Mine_row = (int)(Math.random()*This_line);
			int Mine_col = (int)(Math.random()*This_line);

			if(map[Mine_row][Mine_col] < 0){
				i--;
				continue;
			}
			flag[Mine_row][Mine_col] = true;
			map[Mine_row][Mine_col] = itsMine;

			for(int x=-1; x<=1; x++){
				for(int j=-1; j<=1; j++){
					try{
						
						if(x == 0 && j == 0 || map[Mine_row+x][Mine_col+j] == -1)
							continue;
						
						map[Mine_row+x][Mine_col+j]++;
					}
					catch(Exception e){
						//e.printStackTrace();
					}
				}
			}
		}
		
		for(int i=0; i<This_line; i++){
			for(int j=0; j<This_line; j++){
				System.out.printf("%4d",map[i][j]);
			}
			System.out.println();
		}
		
	}

	public void Flag_check(int i, int j){
		if(map2[i][j] == 8){
			map2[i][j] = 9;
			mine++;
			flag2[i][j] = false;
		}
		
		else if(map2[i][j] == 9){
			map2[i][j] = 8;
			mine--;
			flag2[i][j] = true;
		}
	}
	
	public boolean AllCheck(){
		for(int i=0; i<This_line; i++){
			for(int j=0; j<This_line; j++){
				if(flag2[i][j] == true && flag[i][j] == true)
						Checks++;
			}
		}
		
		if(Checks == minetemp){
			setIsend(true);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void Check(int row, int col){
		if(!open[row][col] && !flag2[row][col]){
			open[row][col] = true;
			
			if(map[row][col] < 0){
				for(int i=0; i<This_line; i++){
					for(int j=0; j<This_line; j++){
						map2[i][j] = map[i][j];
					}
				}
				isend = true;
				return;
			}

			switch(map[row][col]){
			case 0:{
				for(int i=-1; i<=1; i++){
					for(int j=-1; j<=1; j++){
						try{		
							Check(row+i, col+j);
						}
						catch(Exception e){
							//e.printStackTrace();
						}
					}
				}
				map2[row][col] = 0;
				break;
			}
			case 1:{
				map2[row][col] = 1;
				break;
			}
			case 2:{
				map2[row][col] = 2;
				break;
			}
			case 3:{
				map2[row][col] = 3;
				break;
			}
			case 4:{
				map2[row][col] = 4;
				break;
			}
			case 5:{
				map2[row][col] = 5;
				break;
			}
			case 6:{
				map2[row][col] = 6;
				break;
			}
			default:{
				map2[row][col] = 0;
				break;
			}
			}
		}
	}
	
	public void Check2(int row, int col){
		if(open[row][col]){
			int temp = 0;
			for(int i=-1; i<=1; i++){
				for(int j=-1; j<=1; j++){
					try{
						if(flag2[row+i][col+j]){
							if(map[row+i][col+j] >= 0){
								OpenMap();
								isend = true;
							}
							temp++;
						}
					}
					catch(Exception e){
						//e.printStackTrace();
					}
				}
			}
			
			if(map[row][col] == temp){
				for(int i=-1; i<=1; i++){
					for(int j=-1; j<=1; j++){
						try{
							if(flag[row+i][col+j] == true){
								continue;
							}
							if(open[row+i][col+j] == false){
								Check(row+i,col+j);
							}
						}
						catch(Exception e){
							//e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	public void OpenMap(){
		for(int i=0; i<This_line; i++){
			for(int j=0; j<This_line; j++){
				map2[i][j] = map[i][j];
			}
		}
	}
	
	public int getRow(){
		return map.length;
	}

	public int getCol(){
		return map[0].length;
	}
	
	public int getThis_line(){
		return This_line;
	}
	
	public void Setcheck(){
		Checks = 0;
	}
	
	public void setIsend(boolean temp){
		isend = temp;
	}
	
	public boolean getIsend(){
		return isend;
	}
	
	
	public int getCell(int i, int j){
		return map2[i][j];
	}
	
	public int getMine(){
		return mine;
	}
	
	public void setM(int This_line_, int mine_){
		
		isend = false;
		Checks = 0;
		This_line = This_line_;
		mine = mine_;
		minetemp = mine;
		map = new int[This_line][This_line];
		map2 = new int[This_line][This_line];
		flag = new boolean[This_line][This_line];
		flag2 = new boolean[This_line][This_line];
		open = new boolean[This_line][This_line];
		
	}
	
	public boolean getOpen(int row, int col){
		return open[row][col];
	}
	
	public boolean getFlag(int row, int col){
		return flag2[row][col];
	}
	
}
