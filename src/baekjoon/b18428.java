package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//감시 피하기
public class b18428 {
	static String[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N;
	static boolean canHide=false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().split(" ");
		}
		makeWall(0);
		System.out.println(canHide?"YES":"NO");
	}
	static void makeWall(int cnt) {	
		if(canHide) {
			return;
		}
		if(cnt==3) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j].equals("T")) {
						for(int d=0;d<4;d++) {
							int ni = i;
							int nj = j;
							do {
								ni += dx[d];
								nj += dy[d];
								if(ni<0||ni>=N||nj<0||nj>=N) 
									break;
								if(map[ni][nj].equals("O"))
									break;
								if(map[ni][nj].equals("S")) 
									return;	
							}while(true);			
						}
					}
				}
			}
			canHide=true;
			return;
		}else 
			for(int i=0;i<N;i++) 
				for(int j=0;j<N;j++) 
					if(map[i][j].equals("X")) {
						map[i][j]="O";
						makeWall(cnt+1);
						map[i][j]="X";
					}
	}
}
