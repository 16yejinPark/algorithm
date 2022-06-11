package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//소형기관차
public class b2616 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = arr[i-1] + n;
		}
		
		int num = Integer.parseInt(br.readLine()); 
		int dp[][] = new int[4][N+1];
		for(int i=1;i<=3;i++) {
			for(int j=i*num;j<=N;j++) {
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-num]+arr[j]-arr[j-num]);
			}
		}
		System.out.println(dp[3][N]);
	}
}
