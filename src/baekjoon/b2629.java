package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//양팔저울
public class b2629 {
	static int N;
	static int chu[];
	static boolean[] possible;
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		chu = new int[N+1];
		for(int i=1;i<=N;i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		possible = new boolean[40001];
		visited = new boolean[N+1][40001];
		findPossibleWeight(1,0);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			int n = Integer.parseInt(st.nextToken());
			if(possible[n]) {
				System.out.print("Y ");
			}else {
				System.out.print("N ");
			}
		}
	}
	
	static void findPossibleWeight(int n, int tot) {
		if(n>N) 
			return;
		if(visited[n][tot])
			return;
		
		visited[n][tot] = true;
		possible[tot+chu[n]] = true;
		findPossibleWeight(n+1,tot+chu[n]);
		possible[Math.abs(tot-chu[n])] = true;
		findPossibleWeight(n+1,Math.abs(tot-chu[n]));
		possible[tot] = true;
		findPossibleWeight(n+1,tot);
	}
}
