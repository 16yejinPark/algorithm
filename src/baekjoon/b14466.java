package baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//소가 길을 건너간 이유6
public class b14466 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map = null;
	static int[][] road = null;
	static int N;
	static int K;
	static int R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
				
		
		
		road = new int[R][4];
		//길 저장
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			road[i][0] = x1;
			road[i][1] = y1;
			road[i][2] = x2;
			road[i][3] = y2;
		}
		
		//소 표시(1)
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x =Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y]=1;
		}
		
//		for(int x=0;x<=N;x++) {
//			for(int y=0;y<=N;y++) {
//				System.out.print(map[x][y]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
		int answer = 0;
		
		for(int x=0;x<=N;x++) {
			for(int y=0;y<=N;y++) {
				if(map[x][y]==1) {
					int findCow = bfs(x,y)+1; //이만큼 소를 찾았다!
					K-=findCow;
					answer+=K*findCow;
					//System.out.printf("answer=%d\n",answer);
				}
			}
		}
		System.out.println(answer);
	}
	
	static int bfs(int r,int c) {
		int cnt=0;
		boolean[][] visited = new boolean[N+1][N+1];
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[] {r,c});
		visited[r][c]=true;
		map[r][c]=0;
		while(!q.isEmpty()) {
			Integer[] temp = q.remove();
			int x = temp[0];
			int y = temp[1];

//			System.out.printf("%d %d\n",x,y);
			for(int d=0;d<4;d++) {
				boolean is_road = false;
				int nx = x+dx[d];
				int ny = y+dy[d];
				for(int i=0;i<R;i++) {
					if(x==road[i][0]&&y==road[i][1]&&nx==road[i][2]&&ny==road[i][3]) {
						is_road = true;
						break;
					}
					if(nx==road[i][0]&&ny==road[i][1]&&x==road[i][2]&&y==road[i][3]) {
						is_road = true;
						break;
					}
				}
				if(nx>0&&nx<=N&&ny>0&&ny<=N&&!visited[nx][ny]&&!is_road) {
					visited[nx][ny]=true;
					q.add(new Integer[] {nx,ny});
					if(map[nx][ny]==1) {
						map[nx][ny]=0;
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

}
