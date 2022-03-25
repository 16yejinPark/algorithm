package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//색종이 붙이기
public class b17136 {
	static int paperN[] = {5,5,5,5,5};
	static int paperX[][] = {{0},{0,0,1,1},{0,0,0,1,1,1,2,2,2},{0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3},{0,0,0,0,0,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4}};
	static int paperY[][] = {{0},{0,1,0,1},{0,1,2,0,1,2,0,1,2},{0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3},{0,1,2,3,4,0,1,2,3,4,0,1,2,3,4,0,1,2,3,4,0,1,2,3,4}};
	static int map[][] = new int[10][10];
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int total = 0;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1)total++;
			}
		}
		
		dfs(0,0,total);
		if(total == 0) {
			result=0;
		}else if(result == Integer.MAX_VALUE) {
			result=-1;
		}
		System.out.println(result);
	}
	
	static void dfs(int x,int y,int r) {
		if(r==0) {
			int sum = 0;
			for(int i=0;i<5;i++) {
				sum += paperN[i];
			}
			result = Math.min(25-sum, result);
			return;
		}
		
		if(y==10) {
			x++; y=0;
		}
		
		if(map[x][y]==0) {
			dfs(x,y+1,r);
		}else {
			for(int p=0;p<5;p++) {
				if(paperN[p]==0)continue;
				boolean possible = true;
				check: for(int i=0;i<paperX[p].length;i++) {
					for(int j=0;j<paperY[p].length;j++) {
						int nx = x + paperX[p][i]; 
						int ny = y + paperY[p][j]; 
						if(nx>=10||ny>=10||map[nx][ny]==0) {
							possible = false;
							break check;
						}
					}
				}
				if(possible) {
					paperN[p]--;
					for(int i=0;i<paperX[p].length;i++) {
						for(int j=0;j<paperY[p].length;j++) {
							int nx = x + paperX[p][i]; 
							int ny = y + paperY[p][j]; 
							map[nx][ny] = 0;
						}
					}
					dfs(x,y+1,r-((p+1)*(p+1)));
					paperN[p]++;
					for(int i=0;i<paperX[p].length;i++) {
						for(int j=0;j<paperY[p].length;j++) {
							int nx = x + paperX[p][i]; 
							int ny = y + paperY[p][j]; 
							map[nx][ny] = 1;
						}
					}
				}
			}
		}
	}
}
