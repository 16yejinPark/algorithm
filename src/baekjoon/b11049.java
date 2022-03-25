package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//행렬 곱셈 순서
//DP 문제
//https://cocoon1787.tistory.com/318
public class b11049 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[N+1][2];
		for(int n=1;n<=N;n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			matrix[n][0] = a;
			matrix[n][1] = b;
		}
		
		int memo[][] = new int[N+1][N+1];
		for(int gap = 1; gap<N; gap++) {
			for(int start=1;start+gap<=N;start++) {
				memo[start][start+gap]=Integer.MAX_VALUE;
				for(int mid=start;mid<start+gap;mid++) {
					//System.out.printf("%d %d %d\n",start,mid,start+gap);
					memo[start][start+gap] = Math.min(memo[start][start+gap],
							memo[start][mid]+memo[mid+1][start+gap]+(matrix[start][0]*matrix[mid][1]*matrix[start+gap][1]));
				}
			}
		}
		
		System.out.println(memo[1][N]);
	}

}
