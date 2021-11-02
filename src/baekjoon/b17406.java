package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//배열돌리기4
public class b17406 {
	public static int dirs[][] = {{0,1},{1,0},{0,-1},{-1,0}};
	public static ArrayList<int[]> order_list = new ArrayList<int[]>();
	public static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][M + 1];
		int[][] turn = new int[T][3];
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			turn[i][0] = Integer.parseInt(st.nextToken());
			turn[i][1] = Integer.parseInt(st.nextToken());
			turn[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[] order = new int[T];
		boolean visited[] = new boolean[T];
		
		permutation(0,order,visited);

		
		int min=10000000;
		
		for (int[] o : order_list) {
			int[][] temp_arr = new int[N + 1][M + 1];
			
			//원본 훼손을 막기위해 temp_arr생성
			for (int n = 1; n <= N; n++) {
				for (int m = 1; m <= M; m++) {
					temp_arr[n][m] = arr[n][m];
				}
			}
			
			//좌표 옮기기
			for (int i = 0; i < o.length; i++) {
				int row = turn[o[i]][0];
				int col = turn[o[i]][1];
				int size = turn[o[i]][2];
				for(int s=size;s>0;s--) {
					int r = row - s;
					int c = col - s;
					int temp = temp_arr[r][c];
					for(int d=0;d<4;d++) {
						for (int go = 0; go<(s*2); go++) {	
							r = r+dirs[d][0];
							c = c+dirs[d][1];
							int t = temp_arr[r][c];
							temp_arr[r][c] = temp;
							temp = t;
						}
					}
				}
			}
			
			//최소값을 저장하자
			for (int n = 1; n <= N; n++) {
				int sum=0;
				for (int m = 1; m <= M; m++) {
					sum+=temp_arr[n][m];
					if(sum>min)
						break;
				}
				if(min>sum)
					min = sum;
			}
		}
		
		System.out.println(min);
	}
	
	//순열 구하는 함수
	public static void permutation(int cnt,int[] order,boolean[] visited) {
		if(cnt==T) {
			int[] temp_order = new int[order.length];
			for(int i=0;i<order.length;i++) {
				temp_order[i] = order[i];
			}
			order_list.add(temp_order);
			return;
		}
		for(int i=0;i<T;i++) {
			if(!visited[i]) {
				order[cnt]=i;
				visited[i]=true;
				permutation(cnt+1,order,visited);
				visited[i]=false;
			}
		}
	}
	
	
}
