package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1987 {
	public static int[] dr = { -1, 1, 0,0};	//상하좌우
	public static int[] dc = { 0, 0, -1,1 };
	public static int R;
	public static int C;
	public static int max=0;
	public static int count=1;
	public static char[][] map;
	public static boolean[] alpa = new boolean[26];
	
	//알파벳
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		alpa[(int)map[0][0]-65]=true;
		dfs(0,0);
		System.out.println(max);
	}
	
	public static void dfs(int r, int c) {
		for(int d=0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0&&nr<R&&nc>=0&&nc<C&&!alpa[(int)map[nr][nc]-65]) {	
				alpa[(int)map[nr][nc]-65]=true;
				count++;
				dfs(nr,nc);	
				alpa[(int)map[nr][nc]-65]=false;
				count--;	
			}
			if(d==3) {
				if(max < count)
					max = count;			
			}
		}
	}
}
