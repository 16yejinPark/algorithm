package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 수익
public class b4097 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N==0)break;
			
			long max = -1000000L;
			long arr[] = new long[N+1];
			for(int i=1;i<=N;i++) {
				arr[i] = Long.parseLong(br.readLine());
				arr[i] = Math.max(arr[i-1]+arr[i], arr[i]);
				max = Math.max(max, arr[i]);
			}	
			System.out.println(max);
		}
	}
}
