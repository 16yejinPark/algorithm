package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//빵집
public class b3109 {
	public static int[] dr = { -1, 0, 1 };
	public static int R;
	public static int C;

	public static int result = 0;
	public static boolean successed;
	public static String[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new String[R][C];

		for (int r = 0; r < R; r++) {
			String[] temp = br.readLine().split("");
			map[r] = temp;
		}
		for (int i = 0; i < R; i++) {
			successed = false;
			dfs(i, 0);
		}
		System.out.println(result);

	}

	public static void dfs(int r, int c) {
		if(successed) {
			return;
		}
		else if(c==C-1) {
			successed=true;
			result++;
			return;
		}
		
		for(int d=0; d<3; d++) {
			
			int nr = r+dr[d];
			int nc = c+1;
			
			if(nr>=0&&nr<R&&!map[nr][nc].equals("x")) {
				if(!successed)
					map[nr][nc]="x";
				dfs(nr,nc);
				
			}
		}
	}
}