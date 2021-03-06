package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//주식
public class b11501 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++){
			
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			
			long total = 0;
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				total -= arr[i];
			}
			
			
			int memo = arr[N-1];
			for(int i=N-1;i>=0;i--) {
				if(memo<arr[i]) {
					memo = arr[i];
				}
				total += memo;
			}
			
			System.out.println(total);
		}
	}
}
