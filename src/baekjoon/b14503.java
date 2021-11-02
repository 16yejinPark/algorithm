package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//로봇 청소기
public class b14503 {
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//맵 정보
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());	//청소기 정보
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());	//북 동 남 서
		
		int map[][] = new int[N][M];
		boolean visited[][] = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int d= D;
		int r= R;
		int c= C;
		int cnt = 0;
		boolean finishClean = false;
		int tempmap[][] = new int[N][M];
		while(true) {
			//1.현재위치를 청소
			if(!visited[r][c]) {
				cnt+=1;
				visited[r][c] = true;
				tempmap[r][c]=cnt;
			}
			
			//2.현재 방향 기준 왼쪽부터 탐색
			for(int i=0;i<4;i++) {
				d = (d-1)<0?d+3:d-1;
				int nr = r + dx[d];
				int nc = c + dy[d];
				if(nr>=0&&nr<N&&nc>=0&&nc<M&&!visited[nr][nc]&&map[nr][nc]==0) {
					r = nr;
					c = nc;
					break;
				}
				if(i==3) {
					if(map[r-dx[d]][c-dy[d]]==0) {
						r -= dx[d];
						c -= dy[d];
						break;
					}else {
						finishClean = true;
					}
				}
			}
			if(finishClean) {
				break;
			}
		}
		System.out.println(cnt);
	}
}
