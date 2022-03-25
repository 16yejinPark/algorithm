package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//게리맨더링 2
public class b17779 {
	static int N;
	static int map[][];
	static int part[][];
	static boolean visited[][];
	static int dx[] = {0,1,-1,0,0};
	static int dy[] = {0,0,0,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int total = 0;
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		

		int ans = Integer.MAX_VALUE;	
		for(int x=1;x<=N;x++) {
			for(int y=1;y<=N;y++) {
				for(int d1=1;d1+x<=N&&d1<y;d1++) {
					for(int d2=1;d1+d2+x<=N&&d2+y<=N;d2++) {
						part = new int[N+1][N+1];
						visited = new boolean[N+1][N+1];
						int[][] border = {{},{x,y},{x+d2,y+d2},{x+d1,y-d1},{x+d1+d2,y-d1+d2}};
						int gx[] = {0,1,1,-1,-1};
						int gy[] = {0,1,-1,-1,1};

						for(int r=1;r<=4;r++) {
							int tx = border[r][0];
							int ty = border[r][1];
							visited[tx][ty]=true;
						}
						
						// 5구역 표시
						int tx = x;
						int ty = y;
						for(int d=1;d<=4;d++) {
							tx += gx[d];
							ty += gy[d];
							while(!visited[tx][ty]) {
								visited[tx][ty]=true;
								tx +=gx[d];
								ty +=gy[d];
							}
						}
						
						int[] sum = new int[5];
						//1구역 경계선 표시
						for(int r=1;r<x;r++) {
							part[r][y]=1;
							visited[r][y]=true;
							sum[1]+=map[r][y];
						}
						
						//2구역 경계선 표시
						for(int c=y+d2+1;c<=N;c++) {
							part[x+d2][c]=2;
							visited[x+d2][c]=true;
							sum[2]+=map[x+d2][c];
						}
						
						//3구역 경계선 표시
						for(int c=1;c<y-d1;c++) {
							part[x+d1][c]=3;
							visited[x+d1][c]=true;
							sum[3]+=map[x+d1][c];
						}
						
						//4구역 경계선 표시
						for(int r=x+d1+d2+1;r<=N;r++) {
							part[r][y-d1+d2]=4;
							visited[r][y-d1+d2]=true;
							sum[4]+=map[r][y-d1+d2];
						}
						
						//bfs를 돌면서, 어떤 구역인지 표시
						sum[1] = bfs(1,1,1,sum[1]);	//1구역
						sum[2] = bfs(1,N,2,sum[2]);	//2구역
						sum[3] = bfs(N,1,3,sum[3]);	//3구역
						sum[4] = bfs(N,N,4,sum[4]);	//4구역

						
						sum[0] = total - sum[1] - sum[2] - sum[3] - sum[4];
						int minPop = Math.min(sum[0], Math.min(sum[1], Math.min(sum[2], Math.min(sum[3], sum[4]))));
						int maxPop = Math.max(sum[0], Math.max(sum[1], Math.max(sum[2], Math.max(sum[3], sum[4]))));
						ans = Math.min(ans,maxPop-minPop);
					}
				}
			}
		}
		System.out.println(ans);
	}
	
	static int bfs(int r,int c,int no,int sum) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
		part[r][c] = no;
		sum+=map[r][c];
		while(!q.isEmpty()) {
			int[] temp = q.remove();
			int x = temp[0];
			int y = temp[1];
			for(int d=1;d<=4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>0&&nx<=N&&ny>0&&ny<=N&&!visited[nx][ny]) {
					visited[nx][ny]=true;
					part[nx][ny] = no;
					q.add(new int[] {nx,ny});
					sum+=map[nx][ny];
				}
			}
		}
		return sum;
	}
}
