package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//연산자 끼워넣기
public class b14888 {
	static int[] operator = new int[4];
	static int[] nums;
	static int N;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0,nums[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int cnt,int result) {
		if(cnt==(N-1)) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		int temp = result;
		// +
		if(operator[0]>0) {
			operator[0]--;
			result += nums[cnt+1];
			dfs(cnt+1, result);
			operator[0]++;
			result = temp;
		}
		
		// -
		if(operator[1]>0) {
			operator[1]--;
			result -= nums[cnt+1];
			dfs(cnt+1, result);
			operator[1]++;
			result = temp;
		}
		
		// *
		if(operator[2]>0) {
			operator[2]--;
			result *= nums[cnt+1];
			dfs(cnt+1, result);
			operator[2]++;
			result = temp;
		}
		
		// /
		if(operator[3]>0) {
			operator[3]--;
			result /= nums[cnt+1];
			dfs(cnt+1, result);
			operator[3]++;
			result = temp;
		}
		
	}
}
