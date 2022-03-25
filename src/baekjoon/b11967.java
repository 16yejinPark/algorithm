package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//불켜기
public class b11967 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		ArrayDeque<int[]> lightList = new ArrayDeque<int[]>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			lightList.add(new int[] {X,Y,A,B});
		}
		
		boolean[][] visited = new boolean[N+1][N+1];
		boolean[][] lightOn = new boolean[N+1][N+1];
		Queue<int[]> q = new LinkedList<>();
		visited[1][1] = true;
		lightOn[1][1] = true;
		q.add(new int[] {1,1});
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>0&&nx<=N&&ny>0&&ny<=N&&!visited[nx][ny]) {
					visited[nx][ny] = true;
					if(lightOn[nx][ny]) {
						q.add(new int[] {nx,ny});
					}
				}
			}
			
			int size = lightList.size();
			for(int i=0;i<size;i++) {
				int light[] = lightList.poll();
				int lx = light[0];
				int ly = light[1];
				int la = light[2];
				int lb = light[3];
				if(lx==x&&ly==y) {
					lightOn[la][lb] = true;
					if(visited[la][lb]) {
						q.add(new int[] {la,lb});
					}
				}else {
					lightList.add(light);
				}
			}
		}
		
		int cnt = 0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(lightOn[i][j]) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
