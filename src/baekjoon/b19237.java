package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//어른 상어
public class b19237 {
	static int N;
	static int M;	
	static int K;	
	static int dx[] = {0,-1,1,0,0};
	static int dy[] = {0,0,0,-1,1};
	static int[][][] smell;
	static int[][] map;
	static Shark[] sharkList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//격자 크기
		M = Integer.parseInt(st.nextToken());	//상어의 수
		K = Integer.parseInt(st.nextToken());	//k번 이동하면 냄새가 사라진다.
		
		smell = new int[N+1][N+1][2];	//{k,어느 상어}
		map = new int[N+1][N+1];
		sharkList = new Shark[M+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0) {
					sharkList[map[i][j]] = new Shark(map[i][j],i,j);
					smell[i][j][0] = K;
					smell[i][j][1] = map[i][j];
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=M;i++) {
			sharkList[i].d = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=M;i++) {
			for(int d=1;d<=4;d++) {
				st = new StringTokenizer(br.readLine());
				int fst = Integer.parseInt(st.nextToken());
				int scd = Integer.parseInt(st.nextToken());
				int thd = Integer.parseInt(st.nextToken());
				int frth = Integer.parseInt(st.nextToken());
				HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
				hashMap.put(fst, fst);
				hashMap.put(scd, scd*10);
				hashMap.put(thd, thd*100);
				hashMap.put(frth, frth*1000);
				sharkList[i].priority.put(d, hashMap);
			}
		}
		
		//input 끝 -------------------------------------------------------------
		
		int remainShark = M;
		int t = 0;
		while(t<=1000&&remainShark>1) {			
			//동시에 이동함에 주의!
			int[][] tempMap = new int[N+1][N+1];
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(map[i][j]!=0) {
						sharkList[map[i][j]].move();
						int d = sharkList[map[i][j]].d;
						int nx = sharkList[map[i][j]].x;
						int ny = sharkList[map[i][j]].y;
						
						if(tempMap[nx][ny]==0) {
							tempMap[nx][ny] = map[i][j];
						}else {
							if(tempMap[nx][ny]>map[i][j]) {	//서열이 낮은 상어가 있음
								tempMap[nx][ny] = map[i][j];
							}
							remainShark--;
						}
					}
				}
			}	
			//temp를 실제 지도로 옮기기
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					map[i][j] = tempMap[i][j];
				}
			}
			//냄새가 사라진다.
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(smell[i][j][0]>0) {
						smell[i][j][0]--;
						if(smell[i][j][0]==0) {
							smell[i][j][1]=0;
						}
					}
				}
			}
			//현재 위치에 냄새가 생긴다.
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(tempMap[i][j]!=0) {
						smell[i][j][0] = K;
						smell[i][j][1] = tempMap[i][j];
					}
				}
			}
			t++;
		}
		
		System.out.println((t<=1000?t:-1));
		
	}
	static class Shark{
		int n;
		int d;
		int x;
		int y;
		HashMap<Integer,HashMap<Integer,Integer>> priority = new HashMap<>();
		public Shark(int n,int x, int y) {
			super();
			this.n = n;
			this.x = x;
			this.y = y;
		}
		public void move() {
			//먼저 인접한 칸 중 아무 냄새가 없는 칸
			//그런 칸이 없으면 자신의 냄새가 있는 칸
			//이때 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위
			PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->{
				if(o1[0]==o2[0]) {
					return Integer.compare(priority.get(d).get(o1[1]), priority.get(d).get(o2[1]));
				}
				return Integer.compare(o1[0], o2[0]);
			});
			for(int i=1;i<=4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>0&&nx<=N&&ny>0&&ny<=N&&(smell[nx][ny][1]==0||smell[nx][ny][1]==n)) {
					q.add(new int[] {smell[nx][ny][1],i});	//무슨 냄새, 방향
				}
			}
			
			int dir = q.poll()[1];
			this.d = dir;
			this.x = x+dx[dir];
			this.y = y+dy[dir];
		}
	}
}
