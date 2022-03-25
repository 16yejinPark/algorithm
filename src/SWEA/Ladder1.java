package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ladder1 {
	static int start;
	static int[][] ladder = new int[100][100];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int dst = 0;
		for(int i=0;i<10;i++) {
			
			//입력받기
			br.readLine();	
			int x,y;
			for(x=0;x<100;x++) {
				st = new StringTokenizer(br.readLine()," ");
				for(y=0;y<100;y++) {
					ladder[x][y] = Integer.parseInt(st.nextToken());
					if(ladder[x][y]==2) {
						dst = y;
					}
				}
			}
			
			goNextFloor(99,dst);
			System.out.printf("#%d %d\n",i+1,start);
		}
		
	}

	public static void goNextFloor(int x,int y) {
		//기저조건!
		if(x==0) {
			start = y;
			return;
		}
		
		//양옆조사
		if(y-1>=0 && ladder[x][y-1]==1) {
			while(y-1>=0 && ladder[x][y-1]==1) {
				y--;
			}
		}else if(y+1<100 && ladder[x][y+1]==1) {
			while(y+1<100 && ladder[x][y+1]==1) {
				y++;
			}
		}	
		
		//다음 층으로 출발!
		goNextFloor(x-1,y);
	}
}
