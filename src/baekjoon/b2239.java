package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//스도쿠
public class b2239 {
	static int[][] map;
	static ArrayList<int[]> brank = new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String[] nums = br.readLine().split("");
			for (int j = 0; j < 9; j++) {
				int n = Integer.parseInt(nums[j]);
				map[i][j] = n;
				if (n == 0) {
					brank.add(new int[] {i,j});
				}
			}
		}
		dfs(0);
	}

	static void dfs(int i) {
		if(i==brank.size()) {
			for (int x = 0; x < 9; x++) {
				for (int y = 0;y< 9; y++) {
					System.out.print(map[x][y]);
				}
				System.out.println();
			}
			System.exit(0);
		}else {
			int[] temp = brank.get(i);
			int x = temp[0];
			int y = temp[1];
			for(int n=1;n<=9;n++) {
				if(isValid(x,y,n)) {
					//System.out.printf("x: %d y: %d -> %d\n",x,y,n);
					map[x][y]=n;
					dfs(i+1);
					map[x][y]=0;
				}
			}
		}
	}
	
	static boolean isValid(int x, int y, int v) {
		for(int i=0;i<9;i++) {
			if(map[x][i]==v&&i!=y)return false;
			if(map[i][y]==v&&i!=x)return false;
		}
		for(int i=x-(x%3);i<x-(x%3)+3;i++) {
			for(int j=y-(y%3);j<y-(y%3)+3;j++) {
				if(map[i][j]==v&&!(x==i&&y==j))return false;
			}
		}
		return true;
	}	
}
