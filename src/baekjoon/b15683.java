package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//감시
public class b15683 {
	public static int[] dx= {-1,1,0,0}; //상하좌우
	public static int[] dy= {0,0,-1,1}; //상하좌우
	public static int N;
	public static int M;
	public static int[][] map;
	public static boolean[][] underWatch;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0&&map[i][j]!=6) {
					checkWatchArea(map[i][j],i,j);
				}
			}
		}
	}

	public static void checkWatchArea(int type,int x,int y) {
		int cx; int cy;
		switch(type) {
		case 1: 
			for(int d=0;d<4;d++) {
				
			}
			break;
		case 2: 
			for(int d=0;d<2;d++) {
				
			}
			break;
		case 3: 
			for(int d=0;d<4;d++) {
				
			}
			break;
		case 4: 
			for(int d=0;d<4;d++) {
				
			}
			break;
		case 5: 
			for(int d=0;d<4;d++) {
				cx = x;
				cy = y;
				while(true) {
					cx += dx[d];
					cy += dy[d];
					if(cx>=0&&cx<N&&cy>=0&&cy<M&&map[cx][cy]!=6) {
						underWatch[cx][cy]=true;
					}else {
						break;
					}
				}
			}
			break;
		}
	}
}
