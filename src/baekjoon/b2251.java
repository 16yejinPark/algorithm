package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//물통
public class b2251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		boolean[][][] visited = new boolean[A+1][B+1][C+1];
		boolean[] check = new boolean[C+1];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,C});
		visited[0][0][C] = true;

		
		PriorityQueue<Integer> result = new PriorityQueue<Integer>();
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int a = temp[0];
			int b = temp[1];
			int c = temp[2];
			
			//System.out.printf("%d %d %d \n",a,b,c);
			
			if(a==0 && !check[c]) {
				check[c] = true;
				result.add(c);
			}
			
			//A->B
			if(a>=B-b) {
				if(!visited[a-(B-b)][B][c]) {
					visited[a-(B-b)][B][c]=true;
					q.add(new int[] {a-(B-b),B,c});
				}
			}else {
				if(!visited[0][a+b][c]) {
					visited[0][a+b][c]=true;
					q.add(new int[] {0,a+b,c});
				}
			}
			
			//A->C
			if(a>=C-c) {
				if(!visited[a-(C-c)][b][C]) {
					visited[a-(C-c)][b][C]=true;
					q.add(new int[] {a-(C-c),b,C});
				}
			}else {
				if(!visited[0][b][c+a]) {
					visited[0][b][c+a]=true;
					q.add(new int[] {0,b,c+a});
				}
			}
			
			//B->A
			if(b>=A-a) {
				if(!visited[A][b-(A-a)][c]) {
					visited[A][b-(A-a)][c]=true;
					q.add(new int[] {A,b-(A-a),c});
				}
			}else {
				if(!visited[a+b][0][c]) {
					visited[a+b][0][c]=true;
					q.add(new int[] {a+b,0,c});
				}
			}
			
			//B->C
			if(b>=C-c) {
				if(!visited[a][b-(C-c)][C]) {
					visited[a][b-(C-c)][C]=true;
					q.add(new int[] {a,b-(C-c),C});
				}
			}else {
				if(!visited[a][0][b+c]) {
					visited[a][0][b+c]=true;
					q.add(new int[] {a,0,b+c});
				}
			}
			
			//C->A
			if(c>=A-a) {
				if(!visited[A][b][c-(A-a)]) {
					visited[A][b][c-(A-a)]=true;
					q.add(new int[] {A,b,c-(A-a)});
				}
			}else {
				if(!visited[a+c][b][0]) {
					visited[a+c][b][0]=true;
					q.add(new int[] {a+c,b,0});
				}
			}
			
			//C->B
			if(c>=B-b) {
				if(!visited[a][B][c-(B-b)]) {
					visited[a][B][c-(B-b)]=true;
					q.add(new int[] {a,B,c-(B-b)});
				}
			}else {
				if(!visited[a][b+c][0]) {
					visited[a][b+c][0]=true;
					q.add(new int[] {a,b+c,0});
				}
			}
			
		}
		
		while(!result.isEmpty()) {
			System.out.print(result.poll()+" ");
		}
		
		
	}
}
