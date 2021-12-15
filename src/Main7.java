import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main7 {
	static int dx[] = {0,-1,-1,-1,0,1,1,1,0};
	static int dy[] = {0,-1,0,1,1,1,0,-1,-1};
	static int map[][];
	static int visited[][];
	static int N;
	static int answer;
	static Queue<int[]> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			answer=0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new int[N][N];
			int[] hj = {0,0};
			int[] jh = {0,0};
			int mainDir = 0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						hj[0] = i;
						hj[1] = j;
					}else if(map[i][j]==2) {
						jh[0] = i;
						jh[1] = j;
					}
				}
			}
			
			if(hj[0]>jh[0]) {	
				if(hj[1]>jh[1]) {	
					mainDir = 1;
				}else if(hj[1]==jh[1]){	
					mainDir = 2;
				}else {
					mainDir = 3;
				}
			}else if(hj[0]<jh[0]) {
				if(hj[1]<jh[1]) {
					mainDir = 5;
				}else if(hj[1]==jh[1]) {
					mainDir = 6;
				}else {
					mainDir = 7;
				}
			}else {
				if(hj[1]>jh[1]) {
					mainDir = 8;
				}else {
					mainDir = 4;
				}
			}
			
			nextLoc(hj[0],hj[1],mainDir,0);

			while(!q.isEmpty()&&answer==0) {
				
				//print();
				int temp[] = q.poll();
				int x = temp[0];
				int y = temp[1];
				int d = temp[2];
				int n = temp[3];
				nextLoc(x,y,d,n);
			}
			System.out.print("#"+t+" "+(answer==0?"I CAN’T HEAR YOU":answer)+"\n");
		}
	}
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(visited[i][j]+" ");
			}System.out.println();
		}System.out.println();
	}
	static void nextLoc(int x,int y,int d,int n) {

		int nx = 0;
		int ny = 0;
		//System.out.printf("%d %d %d %d\n",x,y,d,n);
		int w = 0;
		if(d%2==1) {
			int nd = d-1;
			nd = nd==0?8:nd;
			nx = x + dx[nd];
			ny = y + dy[nd];
			if(nx>=0&&nx<N&&ny>=0&&ny<N&&map[nx][ny]!=3) {
				w++;
				if(map[nx][ny]==2) {
					answer = n+1;
				}
				q.add(new int[] {nx,ny,nd,n+1});
				visited[nx][ny] = n+1;
			}
			
			nd = d+1;
			nx = x + dx[nd];
			ny = y + dy[nd];
			if(nx>=0&&nx<N&&ny>=0&&ny<N&&map[nx][ny]!=3) {
				w++;
				if(map[nx][ny]==2) {
					answer = n+1;
				}
				q.add(new int[] {nx,ny,nd,n+1});
				visited[nx][ny] = n+1;
			}
		}
		
		if(w==0&&d%2==1)return;
		
		nx = x + dx[d];
		ny = y + dy[d];
		if(nx>=0&&nx<N&&ny>=0&&ny<N&&map[nx][ny]!=3) {
			if(map[nx][ny]==2) {
				answer = n+1;
			}
			q.add(new int[] {nx,ny,d,n+1});
			visited[nx][ny] = n+1;
		}
		
	}
	
}
