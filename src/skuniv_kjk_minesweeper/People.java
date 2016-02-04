package skuniv_kjk_minesweeper;
import java.util.Comparator;

public class People implements Comparator<Object>{
	private String name;
	private int count;
	
	People(String name_, int count_){
		name = name_;
		count = count_;
	}
	
	public String getName(){
		return name;
	}
	
	public int getCount(){
		return count;
	}
	
	public String toString(){
		return name + "," + count;
	}

	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		double one = ((People)arg0).count;
		double two = ((People)arg1).count;
		return one < two ? -1 : (one == two ? 0 : 1);
	}

}
