package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//상어 중학교
public class b21609 {
	static int[][] map;
	static int N;
	static int M;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//한 변의 크기
		M = Integer.parseInt(st.nextToken());	//색상의 개수
		map = new int[N][N];
		int score = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			//가장 큰 blockGroup 찾기
			int[][] visited = new int[N][N];
			int n=1;
			BlockGroup maxGroup = new BlockGroup(0,0,-1,0,n);
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]!=-1&&map[i][j]!=0&&visited[i][j]==0&&map[i][j]!=9) {
						BlockGroup result = findBlockGroup(i,j,visited,n);
						//maxGroup 갱신
						if(maxGroup.size<result.size) {
							maxGroup = new BlockGroup(i,j,result.size,result.rb,result.n); ;
						}else if(maxGroup.size==result.size) {
							if(maxGroup.rb<result.rb) {
								maxGroup = new BlockGroup(i,j,result.size,result.rb,result.n); ;
							}else if(maxGroup.rb==result.rb) {
								if(maxGroup.x<i) {
									maxGroup = new BlockGroup(i,j,result.size,result.rb,result.n); ;
								}else if(maxGroup.x==i) {
									if(maxGroup.y<j) {
										maxGroup = new BlockGroup(i,j,result.size,result.rb,result.n); ;
									}
								}
							}
						}
						n++;
					}
				}
			}	
			if(maxGroup.size<2) {
				break;
			}
			visited = new int[N][N];
			findBlockGroup(maxGroup.x, maxGroup.y, visited, 9);
			//remove block
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visited[i][j]==9) {
						map[i][j]=9;
					}
				}
			}
			score += maxGroup.size * maxGroup.size;
			gravity();
			turn90();
			gravity();
		}
		System.out.println(score);
	}
	
	//bfs
	static BlockGroup findBlockGroup(int r, int c, int[][] visited, int n) {
		Queue<int[]> q = new LinkedList<int[]>(); 
		q.add(new int[] {r,c});
		visited[r][c] = n;
		int size = 0;
		int rn = 0;
		while(!q.isEmpty()){
			int[] temp = q.remove();
			int x = temp[0];
			int y = temp[1];
			size++;
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<N&&visited[nx][ny]!=n&&(map[nx][ny] == 0 || map[nx][ny] == map[r][c])) {
					visited[nx][ny]=n;
					q.add(new int[] {nx,ny});
					if(map[nx][ny] == 0) {
		                  rn++;
		            }
				}
			}
			
		}
		return new BlockGroup(r,c,size,rn,n);	//기준 블록 x,y,size,RainbowNum
	}
	
	//gravity
	static void gravity() {
		for(int i=N-2;i>=0;i--) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==9)continue;
				if(map[i][j]==-1)continue;
				int color = map[i][j];
				int nx = i+1;
				while(true) {
					if(nx == N)break;
					if(map[nx][j]!=9)break;
					nx++;
				}
				nx--;
				map[i][j]=9;
				map[nx][j]=color;
			}
		}
	}
	
	static void turn90() {
		int[][] tempmap = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				tempmap[N-j-1][i] = map[i][j];
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = tempmap[i][j];
			}
		}
	}
	
	
	static class BlockGroup{
		int x;
		int y;
		int size;
		int rb;
		int n;
		public BlockGroup(int x, int y, int size, int rb, int n) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.rb = rb;
			this.n = n;
		}
		
	}
	
}
