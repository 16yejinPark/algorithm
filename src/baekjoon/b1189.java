package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//컴백홈
public class b1189 {
	static int total=0;
	static int R;
	static int C;
	static int K;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int r=0;r<R;r++) {
			map[r]=br.readLine().toCharArray();
		}
		visited[R-1][0]=true;
		dfs(R-1,0,1);
		System.out.println(total);
	}
	static void dfs(int r,int c,int cnt) {
		if(r==0&&c==C-1) {
			if(cnt==K)
				total++;
			return;
		}else {
			for(int d=0;d<4;d++) {
				int nr = r+dx[d];
				int nc = c+dy[d];
				if(nr>=0&&nr<R&&nc>=0&&nc<C&&map[nr][nc]!='T'&&!visited[nr][nc]) {
					visited[nr][nc]=true;
					dfs(nr,nc,cnt+1);
					visited[nr][nc]=false;
				}
			}
		}
	}
}
