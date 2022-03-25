package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//중복 조합
public class b15652 {
	public static int N;
	public static int M;
	public static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[M];
		rep_combination(0,1);
	}
	public static void rep_combination(int cnt,int start) {
		if(cnt == M) {
			System.out.println(Arrays.toString(num));
			return;
		}
		for(int i=start;i<=N;i++) {
			num[cnt] = i;
			rep_combination(cnt+1,i);
		}
		
	}
}
