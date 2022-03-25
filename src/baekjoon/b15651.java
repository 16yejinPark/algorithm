package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//중복 순열
public class b15651 {
	public static int N;
	public static int M;
	public static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[M];
		rep_permutation(0);
		
	}
	public static void  rep_permutation(int cnt) {
		if(cnt == M) {
			System.out.println(Arrays.toString(num));
			return;
		}
		for(int i=1;i<=N;i++) {
			num[cnt] = i;
			rep_permutation(cnt+1);
		}
	}
}
