package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//미네랄
public class b2933 {
	static char[][] map;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int r=0;r<R;r++) {
			map[r]=br.readLine().toCharArray();
		}
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			int h = R-Integer.parseInt(st.nextToken());
					
			//왼쪽에서 돌날라옴
			if(n%2==0) {
				for(int c=0;c<C;c++) {
					if(map[h][c]=='x') {
						map[h][c]='.';
						break;
					}
				}
			}
			//오른쪽에서 돌날라옴
			else {
				for(int c=C-1;c>=0;c--) {
					if(map[h][c]=='x') {
						map[h][c]='.';
						break;
					}
				}
			}
			
			//바닥에 붙어있는 세그먼트 true로 표시
			boolean[][] visited = new boolean[R][C];
			for(int c=0;c<C;c++) {
				if(!visited[R-1][c]&&map[R-1][c]=='x') {
					bfs(R-1,c,R,C,visited);
				}
			}
			
			//바닥에 붙어있지 않은 세그먼트 중 전체를 돌며 얼마만큼 내려야 하는지 계산
			int goDown = R;
			for(int r=R-2;r>=0;r--) {
				for(int c=0;c<C;c++) {
					if(!visited[r][c]&&map[r][c]=='x') {
						int cnt = 0;
						while(true) {
							if(r+cnt>=R-1||visited[r+cnt+1][c]) {
								goDown = Math.min(goDown, cnt);
								break;
							}else {
								cnt++;
							}
						}
					}
				}
			}

			//위에 계산한 값을 바탕으로 아래로 이동
			for(int r=R-2;r>=0;r--) {
				for(int c=0;c<C;c++) {
					if(!visited[r][c]&&map[r][c]=='x') {
						map[r+goDown][c]='x';
						map[r][c]='.';
					}
				}
			}		
		}
		
		//결과 프린트
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
		
	}
	static void bfs(int startX,int startY,int r,int c,boolean[][] visited) {
		visited[startX][startY] = true;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {startX,startY});
		while(!q.isEmpty()) {
			int[] temp = q.remove();
			int x = temp[0];
			int y = temp[1];
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<r&&ny>=0&&ny<c&&!visited[nx][ny]&&map[nx][ny]=='x') {
					visited[nx][ny]=true;
					q.add(new int[] {nx,ny});
				}
			}
		}
	}
}
