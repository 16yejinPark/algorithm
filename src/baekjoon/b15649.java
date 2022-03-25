package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//N과 M(1)
//순열
public class b15649 {
	public static int N;
	public static int M;
	public static int[] num;
	public static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		num = new int[M];
		permutation(0);
	}
	public static void permutation(int cnt) {
		if(cnt == M) {
			System.out.println(Arrays.toString(num));
			return;
		}
		for(int i=1;i<=N;i++) {
			if(visited[i]) continue;
			num[cnt] = i;
			visited[i] = true;
			permutation(cnt+1);
			visited[i]=false;
		}
		
	}
}
