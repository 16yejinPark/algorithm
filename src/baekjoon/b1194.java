package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//달이 차오른다 가자
public class b1194 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char[][] map = new char[M][N]; 
		boolean[][][] visited = new boolean[M][N][64];

		int startM=0;
		int startN=0;
		for(int m=0;m<M;m++) {
			map[m] = br.readLine().toCharArray();
			for(int n=0;n<N;n++) {
				if(map[m][n]=='0') {
					startM=m;
					startN=n;
				}
			}
		}
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {startM,startN,0,0});	//시작점과 cnt,key
		visited[startM][startN][0]=true;
		while(!q.isEmpty()) {
			int[] point = q.remove();
			int x = point[0];
			int y = point[1];
			int cnt = point[2];
			int key = point[3];
			//System.out.printf("%d %d %d %d %c\n",x,y,cnt,key,map[x][y]);
			
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<M&&ny>=0&&ny<N&&map[nx][ny]!='#'&&!visited[nx][ny][key]) {
					visited[nx][ny][key]=true;
					if(map[nx][ny]>='A'&&map[nx][ny]<='F') {
						//해당 열쇠 가지고 있는지 확인
						if(((1<<(map[nx][ny])-'A')&key)>0) {
							q.add(new int[] {nx,ny,cnt+1,key});
							continue;
						}
					}else if(map[nx][ny]>='a'&&map[nx][ny]<='f') {
						int nk = (1<<(map[nx][ny]-'a'))|key;
						if(!visited[nx][ny][nk]) {
							visited[nx][ny][nk]=true;
							q.add(new int[] {nx,ny,cnt+1,nk});
							continue;
						}else {
							q.add(new int[] {nx,ny,cnt+1,key});
							continue;
						}
					}else if(map[nx][ny]=='1') {
						System.out.println(cnt+1);
						return;
					}else {
						q.add(new int[] {nx,ny,cnt+1,key});
						continue;
					}
				}
			}
		}
		System.out.println(-1);
	}
}
