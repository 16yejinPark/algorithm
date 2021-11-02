package ssafy;

import java.util.ArrayList;

public class Test {
	static ArrayList<ArrayList<Integer>> abs;
	public static void main(String[] args){
		System.out.println(args[0]);
		
		abs = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<9;i++) {
			abs.add(new ArrayList<Integer>());
		}
		int i=0;
		abs.get(i).size();
	}
}

