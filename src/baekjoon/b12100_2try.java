package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b12100_2try {
	static int N;
	static int[][] board;
	static int max = 0;

	// 2048(Easy)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);
		System.out.println(max);

	}

	static public void dfs(int n) {
		if (n == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max < board[i][j]) {
						max = board[i][j];
					}
				}
			}
			return;
		}

		int[][] copy = new int[N][N];
		copy(board, copy);
		for(int i=0;i<4;i++) {
			turnClockWise(i);
			play();
			turnAntiClockWise(i);
			dfs(n + 1);
			copy(copy, board);
		}
	}

	static public void play() {
		boolean changed[][] = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				int x = j;
				if (board[i][x] == 0) continue;
				for(int k=j-1;k>=0;k--) {
					if(board[i][k]==0) {
						board[i][k] = board[i][x];
						board[i][x] = 0;
					}else if(board[i][k]==board[i][x] && !changed[i][k] && !changed[i][x]) {
						changed[i][k] = true;
						board[i][k] = board[i][x]*2;
						board[i][x] = 0;
					}else {
						break;
					}
					x--;
				}
			}
		}
	}

	static public void turnClockWise(int rep) {
		int[][] copy = new int[N][N];
		for (int r = 0; r < rep; r++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					copy[j][N - i - 1] = board[i][j];
				}
			}
			copy(copy, board);
		}
	}

	static public void turnAntiClockWise(int rep) {
		int[][] copy = new int[N][N];
		for (int r = 0; r < rep; r++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					copy[N - j - 1][i] = board[i][j];
				}
			}
			copy(copy, board);
		}
	}

	static public void copy(int[][] from, int[][] to) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
}
