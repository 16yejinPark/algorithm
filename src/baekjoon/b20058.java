package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b20058 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int NN = Integer.parseInt(st.nextToken());	//map범위
		int Q = Integer.parseInt(st.nextToken());	//파이어스톰 횟수
		int N = (int) Math.pow(2, NN);
		int map[][] = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int x=0;x<Q;x++) {
			int nn = Integer.parseInt(st.nextToken());	//파이어스톰 사이즈
			int n = (int)Math.pow(2, nn);
			//90도 회전
			int temp[][] = new int[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					int ir = i%n;
					int iq = i-ir;
					int jr = j%n;
					int jq = j-jr;
					temp[jr+iq][n-ir-1+jq] = map[i][j];
				}
			}
			
			//원본으로 돌아옴
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					map[i][j]=temp[i][j];
				}				
			}
			
			//얼음이 있는 칸을 3칸 이상 인접하지 않은 얼음 1씩 줄어듦
			boolean melt[][] = new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]!=0) {
						int adjIce = 0;
						for(int d=0;d<4;d++) {
							int ni = i + dx[d];
							int nj = j + dy[d];
							if(ni>=0&&ni<N&&nj>=0&&nj<N&&map[ni][nj]>0) {
								adjIce++;
							}
						}
						if(adjIce<3) {
							melt[i][j]=true;
						}
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(melt[i][j])
						map[i][j]--;
				}
			}
		}
		
		//남아있는 얼음의 합
		int total=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				total+=map[i][j];
			}
		}
		System.out.println(total);
		
		//남아있는 얼음중 가장 큰 덩어리의 칸 개수
		boolean visited[][] = new boolean[N][N];
		int maxSize = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]&&map[i][j]!=0) {
					int cnt = 0;
					visited[i][j]=true;
					Queue<int[]> q = new LinkedList<int[]>();
					q.add(new int[] {i,j});
					while(!q.isEmpty()) {
						int[] temp = q.remove();
						int x = temp[0];
						int y = temp[1];
						cnt++;
						for(int d=0;d<4;d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							if(nx>=0&&nx<N&&ny>=0&&ny<N&&!visited[nx][ny]&&map[nx][ny]>0) {
								visited[nx][ny]=true;
								q.add(new int[] {nx,ny});
							}
						}
					}
					maxSize = Math.max(maxSize, cnt);
				}
			}
		}
		System.out.println(maxSize);
	}
}
