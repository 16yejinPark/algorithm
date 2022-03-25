package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//다각형의 면적
public class b2166 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		long arr[][] = new long[N+1][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Long.parseLong(st.nextToken());
			arr[i][1] = Long.parseLong(st.nextToken());
		}
		arr[N][0] = arr[0][0];
		arr[N][1] = arr[0][1];
		
		long answer = 0L;
		for(int i=0;i<2;i++) {
			long sum = 0L;
			if(i==0) {
				for(int j=0;j<N;j++) {
					sum += arr[j][0] * arr[j+1][1];
				}
				answer += sum;
			}else if(i==1) {
				for(int j=0;j<N;j++) {
					sum += arr[j][1] * arr[j+1][0];
				}
				answer -= sum;
			}
		}
		System.out.println(String.format("%.1f", Math.abs(answer)*0.5));
	}
}
