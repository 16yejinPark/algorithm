package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//스타트 택시
public class b19238 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	
		int M = Integer.parseInt(st.nextToken());	//승객의 수
		int F = Integer.parseInt(st.nextToken());	//초기 연료
		
		int map[][] = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		ArrayDeque<int[]> cstms = new ArrayDeque<int[]>();
		for(int m=1;m<=M;m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int xx = Integer.parseInt(st.nextToken());
			int yy = Integer.parseInt(st.nextToken());
			cstms.add(new int[] {x,y,xx,yy});
		}
		
		while(cstms.size()!=0) {
			
			//승객을 고르자
			int[] cstm = getDistToCstm(map,N,M,X,Y,cstms);
			if(cstm==null) {
				F=-1;
				break;
			}
			if(F<cstm[0]) {
				F=-1;
				break;
			}

			//출발지로 이동
			X = cstm[1];
			Y = cstm[2];
			F-=cstm[0];
			
			//목적지로 이동
			int move = goToDstn(map,N,M,X,Y,cstm);
			if(F<move) {
				F=-1;
				break;
			}
			//X,Y값 변경
			X = cstm[3];
			Y = cstm[4];
			F-=move;
			F+=(move*2);
		}
		System.out.println(F);
	}
	

	static int[] getDistToCstm(int[][] map,int N,int M,int X,int Y,ArrayDeque<int[]> cstms) {
		
		//택시가 있는자리에도 고객있는지 확인
		boolean findCustomer = false;
		int[] result= null;
		int s = cstms.size();
		for(int m=0;m<s;m++) {
			int[] cstm = cstms.removeFirst();
			if(cstm[0]==X&&cstm[1]==Y) {
				findCustomer = true;
				result =  new int[] {0,cstm[0],cstm[1],cstm[2],cstm[3]};
			}else {
				cstms.addLast(cstm);
			}
		}
		if(findCustomer) {
			return result;
		}
		
		//최단 거리에 있는 고객 찾기
		boolean visited[][] = new boolean[N+1][N+1];
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->(Integer.compare(o1[2], o2[2])));
		PriorityQueue<int[]> cstmQ = new PriorityQueue<int[]>(
				(o1,o2)->{
						if(o1[1]==o2[1]) {
							if(o1[2]==o2[2]) {
								return Integer.compare(o1[3], o2[3]);
							}else {
								return Integer.compare(o1[2], o2[2]);
							}
						}
						return Integer.compare(o1[1], o2[1]);
				});
		q.add(new int[] {X,Y,0});
		visited[X][Y]=true;
		
		int minDist = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int temp[] = q.remove();
			int x = temp[0];
			int y = temp[1];
			int dist = temp[2];
			
			if(dist>minDist)break;
			
			//System.out.printf("%d %d\n",x,y);
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>0&&nx<=N&&ny>0&&ny<=N&&!visited[nx][ny]&&map[nx][ny]==0) {
					visited[nx][ny] = true;
					q.add(new int[] {nx,ny,dist+1});
					s = cstms.size();
					for(int m=0;m<s;m++) {
						int[] cstm = cstms.removeFirst();
						if(cstm[0]==nx&&cstm[1]==ny) {
							minDist = Math.min(minDist,dist+1);
							cstmQ.add(new int[] {m,dist+1,nx,ny});
						}
						cstms.addLast(cstm);
					}
				}
			}	
		}	
		
		if(cstmQ.isEmpty())return result;
		
		int[] pick= cstmQ.remove();
		
		s = cstms.size();
		for(int m=0;m<s;m++) {
			int[] cstm = cstms.removeFirst();
			if(m==pick[0]) {
				result = new int[]{pick[1],cstm[0],cstm[1],cstm[2],cstm[3]};
			}else {
				cstms.addLast(cstm);
			}
		}
		return result;
	}
	
	static int goToDstn(int[][] map,int N,int M,int X,int Y,int[] cstm) {
		boolean visited[][] = new boolean[N+1][N+1];
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->(Integer.compare(o1[2], o2[2])));
		q.add(new int[] {X,Y,0});
		visited[X][Y]=true;
		
		while(!q.isEmpty()) {
			int temp[] = q.remove();
			int x = temp[0];
			int y = temp[1];
			int dist = temp[2];
			//System.out.printf("%d %d\n",x,y);
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>0&&nx<=N&&ny>0&&ny<=N&&!visited[nx][ny]&&map[nx][ny]==0) {
					visited[nx][ny] = true;
					q.add(new int[] {nx,ny,dist+1});
					if(nx==cstm[3]&&ny==cstm[4]) {
						return dist+1;
					}
				}
			}	
		}	
		return Integer.MAX_VALUE;
	}
}
