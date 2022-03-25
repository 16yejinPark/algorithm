package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//랜선 자르기
public class b1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());	//랜선의 수
		int N = Integer.parseInt(st.nextToken());	//필요한 랜선의 수
		
		int[] wires = new int[K];
		long sum = 0L;
		for(int k=0;k<K;k++) {
			wires[k] = Integer.parseInt(br.readLine());
			sum += wires[k];
		}
		
		long start = 1;
		long result = 0;
		long end = sum/N;
		while(start<=end) {
			long mid = (start+end)/2;
			int cnt =0;
			for(int i=0;i<K;i++) {
				cnt += wires[i]/mid;
			}

			if(cnt >= N) {
				start = mid + 1;
				result = Math.max(result, mid);
			}else {
				end = mid - 1;
			}
		}
		System.out.println(result);
	}
}
