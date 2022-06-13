package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1,2,3 더하기 4
public class b15989 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N+1][4];
			
			if(3<=N)arr[3][1]=1;
			if(3<=N)arr[3][2]=1;
			if(3<=N)arr[3][3]=1;
			if(2<=N)arr[2][1]=1;
			if(2<=N)arr[2][2]=1;
			if(1<=N)arr[1][3]=1;
			
			for(int i=4;i<=N;i++) {
				arr[i][1] = arr[i-1][1];
				arr[i][2] = arr[i-2][1]+arr[i-2][2];
				arr[i][3] = arr[i-3][1]+arr[i-3][2]+arr[i-3][3];
			}
			
			int answer = arr[N][1] + arr[N][2] + arr[N][3];
			if(N<=3) answer--;
			System.out.println(answer);
		}
	}
}
