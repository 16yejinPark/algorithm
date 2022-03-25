package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//야구
public class b17281 {
	static int N;
	static int score = 0;
	static int record[][];
	static int[] turn = new int[10];
	static boolean[] visited = new boolean[10];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		record = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				record[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
		// 안타: 1 //2루타: 2 //3루타: 3 //홈런: 4 //아웃: 0
		per(1);
		System.out.println(score);
	}

	static void per(int n) {
		if (n == 10) {
			getScore();
			return;
		} else if (n == 4) {
			turn[n] = 1;
			per(n + 1);
		} else {
			for (int i = 2; i <= 9; i++) {
				if (!visited[i]) {
					visited[i] = true;
					turn[n] = i;
					per(n + 1);
					visited[i] = false;
				}
			}
		}
	}

	static void getScore() {
		int sum = 0;
		int n = 0;
		for (int i = 1; i <= N; i++) {
			int out = 0;	
			boolean ru[] = new boolean[4];
			while (out < 3) {
				n++;
				if(n>9)n=1;
				
				int t = record[i][turn[n]];
				if (t == 0) {
					out++;
					continue;
				}
				
				for(int k=3;k>0;k--) {
					if(ru[k]) {
						ru[k] = false;
						if(k+t>3) {
							sum++;
						}else {
							ru[k+t] = true;
						}
					}
				}
				
				if(t==4) sum++;
				else 
					ru[t] = true;
			}
		}
		if(score<sum) {
			score = sum;
		}
	}
}
