package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//다리만들기2
//크루스칼과 bfs
public class b17472 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[] parent;
	static int map[][];
	static int[][] visited;
	static ArrayList<Edgeee> list; 
	static int find(int x) {
		if(x==parent[x])
			return x;
		else {
			parent[x] = find(parent[x]);
			return parent[x];
		}
	}
	
	static void union(int x, int y) {
		x=parent[x];
		y=parent[y];
		if(x!=y) {
			parent[y] = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m]=Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new int[N][M];
		int cnt=0;
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				if(map[n][m]==1&&visited[n][m]==0) {
					cnt++;	//섬의 개수를 카운트
					bfs(n,m,N,M,cnt);
				}
			}
		}
		parent = new int[cnt+1];
		for(int i=1;i<=cnt;i++) {
			parent[i]=i;
		}
		
		list = new ArrayList<>();
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				if(visited[n][m]!=0) {
					for(int d=0;d<4;d++) {
						int tn=n;
						int tm=m;
						int len=0;
						do{
							tn+=dx[d];
							tm+=dy[d];			
							if(tn<0||tn>=N||tm<0||tm>=M||visited[tn][tm]==visited[n][m]) {
								break;
							}
							if(visited[tn][tm]!=0) {
								if(len>1)
									list.add(new Edgeee(visited[n][m],visited[tn][tm],len));
								break;
							}
							len++;
						}while(true);
					}
				}
			}
		}

		Collections.sort(list);
		int total=0;
		for(Edgeee edge : list) {
			if(find(edge.a)!=find(edge.b)) {
				total+=edge.len;
				union(edge.a,edge.b);
			}
		}
		
		boolean allConnected = true;
		for(int i=1;i<=cnt;i++) {
			for(int j=1;j<=cnt;j++) {
				if(find(i)!=find(j)) {
					allConnected = false;
					break;
				}
			}
		}
		
		if(!allConnected)
			total=-1;
		System.out.println(total);
	}
	static void bfs(int n,int m,int N,int M,int num) {
		visited[n][m]=num;
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		q.add(new Integer[]{n,m});
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.remove();
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<M&&visited[nx][ny]==0&&map[nx][ny]==1) {
					visited[nx][ny]=num;
					q.add(new Integer[]{nx,ny});
				}
			}
		}
		
	}
}

class Edgeee implements Comparable<Edgeee>{
	int a;
	int b;
	int len;

	public Edgeee(int a, int b, int len) {
		super();
		this.a = a;
		this.b = b;
		this.len = len;
	}

	@Override
	public int compareTo(Edgeee o) {
		return Integer.compare(this.len, o.len);
	}
}
