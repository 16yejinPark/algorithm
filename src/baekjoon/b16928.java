package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//뱀과 사다리 게임
//Hint)무조건 올라타야함!
public class b16928 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//사다리의 수
		int M = Integer.parseInt(st.nextToken());	//뱀의 수
		
		int ladderOrSnake[] = new int[101];
		//Arrays.fill(memo, 50000);
		
		for(int i=0;i<N+M;i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());	
			int m = Integer.parseInt(st.nextToken());	
			ladderOrSnake[n] = m;
		}
		boolean visited[] = new boolean[101];
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->{
			return Integer.compare(o1[0], o2[0]);
		});
		q.add(new int[] {0,1});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int n = temp[0];
			int player = temp[1];
			
			//System.out.println("player : "+player +"   n : "+n );
			for(int i=1;i<=6;i++) {
				int nPlayer = player + i;
				if(nPlayer==100) {
					System.out.println(n+1);
					System.exit(0);
				}
				if(nPlayer<=100&&!visited[nPlayer]) {
					visited[nPlayer] = true;
					
					if(ladderOrSnake[nPlayer]!=0) {
						q.add(new int[] {n+1,ladderOrSnake[nPlayer]});
					}else {
						q.add(new int[] {n+1,nPlayer});
					}
				}
			}
		}
	}
}
