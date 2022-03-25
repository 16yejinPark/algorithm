package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//토마토
public class b7576 {
	public static int[][] map;
	public static boolean[][] beforeVisited;
	public static boolean[][] visited;
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	public static int N;
	public static int M;
	public static Queue<Integer[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		beforeVisited = new boolean[N][M];
		map = new int[N][M];
		queue = new LinkedList<Integer[]>();
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m]=Integer.parseInt(st.nextToken());
				if(map[n][m]==1) {
					queue.add(new Integer[] {n,m});
				}
			}	
		}
		
		bfs();
		int answer = -1;
		outer:for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				if(map[n][m]==0) {
					answer = -1;
					break outer;
				}else if(answer<map[n][m]) {
					answer=map[n][m];
				}
			}	
		}
		if(answer!=-1)
			answer--;
		System.out.println(answer);
	}
	private static void bfs() {
		while(!queue.isEmpty()) {
			Integer[] tomato = queue.remove();
			for(int i=0;i<4;i++) {
				int nn = tomato[0]+dx[i];
				int nm = tomato[1]+dy[i];
				if(nn>=0&&nn<N&&nm>=0&&nm<M&&map[nn][nm]==0) {
					queue.add(new Integer[] {nn,nm});
					map[nn][nm]=map[tomato[0]][tomato[1]]+1;
				}
			}
			
		}
		
	}

}
