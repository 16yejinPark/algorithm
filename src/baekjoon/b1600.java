package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//말이 되고픈 원숭이
public class b1600 {
	static int[] horseX = {-1,-2,-2,-1,1,2,2,1};
	static int[] horseY = {-2,-1,1,2,-2,-1,1,2};
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int K = Integer.parseInt(br.readLine());
		st =new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] map = new int[H][W]; 
		int[][][] visited = new int[H][W][K+1]; 
		for(int h=0;h<H;h++) {
			st = new StringTokenizer(br.readLine());
			for(int w=0;w<W;w++) {
				map[h][w]=Integer.parseInt(st.nextToken());
			}
		}
		bfs(H,W,K,map,visited);
		int min = Integer.MAX_VALUE;
		for(int i=0;i<=K;i++) {
			if(visited[H-1][W-1][i]!=0&&min>visited[H-1][W-1][i]) {
				min=visited[H-1][W-1][i];
			}
		}
		if(W==1&&H==1) {
			min=0;
		}
		if(min==Integer.MAX_VALUE) {
			min=-1;
		}
		System.out.println(min);
	}
	
	public static void bfs(int X,int Y,int K,int[][] map,int[][][] visited) {
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[] {0,0,K});
		while(!q.isEmpty()) {
			int x= q.peek()[0];
			int y= q.peek()[1];
			int k= q.peek()[2];
			//System.out.printf("%d %d %d = %d\n",x,y,k,visited[x][y][k]);
			q.remove();
			for(int i=0;i<8;i++) {
				int nx = x+horseX[i];
				int ny = y+horseY[i];
				if(nx>=0&&nx<X&&ny>=0&&ny<Y&&k>0&&map[nx][ny]==0){
					if((visited[nx][ny][k-1]!=0&&visited[nx][ny][k-1]>visited[x][y][k]+1)) {
						visited[nx][ny][k-1]=visited[x][y][k]+1;
					}else if(visited[nx][ny][k-1]==0) {
						visited[nx][ny][k-1]=visited[x][y][k]+1;
						q.add(new Integer[] {nx,ny,k-1});
					}
				}	
			}
			
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx>=0&&nx<X&&ny>=0&&ny<Y&&map[nx][ny]==0){
					if((visited[nx][ny][k]!=0&&visited[nx][ny][k]>visited[x][y][k]+1)) {
						visited[nx][ny][k]=visited[x][y][k]+1;
					}else if(visited[nx][ny][k]==0) {
						visited[nx][ny][k]=visited[x][y][k]+1;
						q.add(new Integer[] {nx,ny,k});
					}
				}	
			}
			
		}
	}
	
}
