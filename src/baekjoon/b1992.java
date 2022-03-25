package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//쿼드트리
public class b1992 {
	public static String[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		for(int i=0;i<N;i++) {
			map[i]=br.readLine().split("");
		}
		
		quadTree(N,0,0);
		
		
	}
	public static void quadTree(int size,int x,int y) {
		
		//System.out.printf("%d %d\n",x,y);
		String start = map[x][y];
		if(size==1) {
			System.out.print(start);
			return;
		}
		
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				if(!start.equals(map[i][j])) {
					size/=2;
					System.out.print("(");
					quadTree(size,x,y);
					quadTree(size,x,y+size);
					quadTree(size,x+size,y);
					quadTree(size,x+size,y+size);
					System.out.print(")");
					return;
				}
			}
		}
		System.out.print(start);
	}
	
}
