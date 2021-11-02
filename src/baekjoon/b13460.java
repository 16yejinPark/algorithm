package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//구슬 탈출 2
public class b13460 {
	static char[][] map;
	static int rx;
	static int ry;
	static int bx;
	static int by;
	static int N;
	static int M;	
	static int min=Integer.MAX_VALUE;
	static int[] dx = {-1,1,0,0};	
	static int[] dy = {0,0,-1,1};	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j]=str.charAt(j);
				if(map[i][j]=='R') {
					map[i][j]='.';
					rx = i;
					ry = j;
				}else if(map[i][j]=='B') {
					map[i][j]='.';
					bx = i;
					by = j;
				}
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		boolean visited[][][][] = new boolean[N][M][N][M];
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {rx,ry,bx,by,0});
		visited[rx][ry][bx][by]=true;
		while(!q.isEmpty()) {
			int temp[] = q.remove();
			int rr = temp[0];
			int rc = temp[1];
			int br = temp[2];
			int bc = temp[3];
			int cnt = temp[4];
			if(cnt>=10) {
				break;
			}
			System.out.printf("빨간공(%d, %d) 파란공(%d, %d) cnt: %d\n",rr,rc,br,bc,cnt);
			for(int d=0;d<4;d++) {
				int nrr = rr;
				int nrc = rc;
				boolean redhole = false;
				while(map[nrr+dx[d]][nrc+dy[d]]!='#') {
					nrr += dx[d];
					nrc += dy[d];
					if(map[nrr][nrc]=='O') {
						redhole=true;
						break;
					}
				}
				
				int nbr = br;
				int nbc = bc;
				boolean bluehole = false;
				while(map[nbr+dx[d]][nbc+dy[d]]!='#') {
					nbr += dx[d];
					nbc += dy[d];
					if(map[nbr][nbc]=='O') {
						bluehole=true;
						break;
					}
				}
				
				if(bluehole) {
					continue;
				}
				if(redhole) {
					return cnt+1;
				}
				
				//두 구슬이 같은 위치
				if(nrr==nbr&&nrc==nbc) {
					switch(d) {
					case 0:
						if(rr>br) {	//blue가 더 위쪽
							nrr -= dx[d];
							nrc -= dy[d];
						}else {
							nbr -= dx[d];
							nbc -= dy[d];
						}
						break;
					case 1:
						if(rr>br) {//blue가 더 위쪽
							nbr -= dx[d];
							nbc -= dy[d];
						}else {
							nrr -= dx[d];
							nrc -= dy[d];
						}
						break;
					case 2:
						if(rc<bc) { //blue가 더 오른쪽
							nbr -= dx[d];
							nbc -= dy[d];
						}else { 
							nrr -= dx[d];
							nrc -= dy[d];
						}
						break;
					case 3:
						if(rc<bc) { //blue가 더 오른쪽
							nrr -= dx[d];
							nrc -= dy[d];
						}else {
							nbr -= dx[d];
							nbc -= dy[d];
						}
						break;
					}
				}
				if(!visited[nrr][nrc][nbr][nbc]) {
					visited[nrr][nrc][nbr][nbc]=true;
					q.add(new int[] {nrr,nrc,nbr,nbc,cnt+1});
				}
			}
		}	
		return -1;
	}	
}
