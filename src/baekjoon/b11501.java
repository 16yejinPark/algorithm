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
			int[] max = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Stack<Integer> s = new Stack<>();
			for(int i=N-1;i>=0;i--) {
				while(!s.isEmpty()&&s.peek()<=arr[i]) {
					s.pop();
				}
				if(s.isEmpty())
					max[i] = arr[i];
				else
					max[i] = s.peek();
				s.push(arr[i]);
			}
			
			int cnt = 0;
			long total = 0;
			for(int i=0;i<N;i++) {
				if(i==N-1) {
					total += arr[i]*cnt;
					break;
				}
				if(arr[i]<max[i]) {
					cnt++;
					total -= arr[i];
				}else if(arr[i]==max[i]) {
					total += arr[i]*cnt;
					cnt = 0;
				}
			}
			System.out.println(total);
		}
	}
}
