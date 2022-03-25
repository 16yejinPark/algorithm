package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//N-Queen
public class b9663 {
	static int N;
	static int[] board;
	static long total = 0; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N+1];
		dfs(1);
		System.out.println(total);
	}
	
	static void dfs(int n) {
		if(n>N) {
			total++;
			return;
		}else {
			//System.out.println(n);
			for(int i=1;i<=N;i++) {
				if(isValid(n,i)) {
					board[n]=i;
					dfs(n+1);
					board[n]=0;
				}
			}
		}
	}
	
	static boolean isValid(int n,int newLoc) {
		for(int i=1;i<n;i++) {
			int gap = n-i;
			if(board[i]==newLoc||board[i]-gap==newLoc||board[i]+gap==newLoc)
				return false;
		}
		return true;
	}
}
