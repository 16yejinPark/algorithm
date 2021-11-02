package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//N과 M(2)
//조합
public class b15650 {
	public static int N;
	public static int M;
	private static int[] num;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[M];
		combination(0,1);
	}
	public static void combination(int cnt,int start) {
		if(cnt==M) {
			System.out.println(Arrays.toString(num));
			return;
		}
		for(int i=start;i<=N;i++) {
			num[cnt] = i;
			combination(cnt+1,i+1);
		}
	}
}
