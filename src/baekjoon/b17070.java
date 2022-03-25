package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//파이프 옮기기1
public class b17070 {

	static int N ;
	static int cnt = 0;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(1,2,1);
		System.out.println(cnt);
		
	}
	
	public static void dfs(int x,int y,int pType) {
		if(x==N&&y==N) {
			cnt++;
			return;
		}else {
			switch(pType) {
			case 1:	//가로
				if(y+1<=N&&map[x][y+1]==0) {
					dfs(x,y+1,1);
				}
				if(y+1<=N&&x+1<=N&&map[x][y+1]==0&&map[x+1][y+1]==0&&map[x+1][y]==0) {
					dfs(x+1,y+1,2);
				}
				break;
			case 2:	//대각
				if(y+1<=N&&map[x][y+1]==0) {
					dfs(x,y+1,1);
				}
				if(y+1<=N&&x+1<=N&&map[x][y+1]==0&&map[x+1][y+1]==0&&map[x+1][y]==0) {
					dfs(x+1,y+1,2);
				}
				if(x+1<=N&&map[x+1][y]==0) {
					dfs(x+1,y,3);
				}
				break;
			case 3:	//세로
				if(y+1<=N&&x+1<=N&&map[x][y+1]==0&&map[x+1][y+1]==0&&map[x+1][y]==0) {
					dfs(x+1,y+1,2);
				}
				if(x+1<=N&&map[x+1][y]==0) {
					dfs(x+1,y,3);
				}
				break;
			}
		}
	}
}
