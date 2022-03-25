package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b17822_bestSol {
	
	private static int N, M, T, totCnt, sum = 0;
	private static int[][] circle;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		totCnt = N * M;
		
		circle = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
				sum += circle[i][j];
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			for (int j = x; j <= N; j += x) {
				turn(j - 1, d, k);
			}
			
			if (eraze() == 0) {
				updown();
			} else {
                if (sum == 0) {
                    break;
                }
            }
		}
		System.out.println(sum);
	}
	
	private static void turn(int x, int d, int k) {
		int[] newArray = new int[M];
		
		for (int i = 0; i < M; i++) {
			if (d == 0) {
				newArray[i] = circle[x][goLeft(i, k)];
			} else {
				newArray[i] = circle[x][goRight(i, k)];
			}
		}
		circle[x] = newArray;
	}
	
	private static int eraze() {
		int[][] newCircle = new int[N][M];
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (circle[i][j] == 0) {
					continue;
				}
				
				if (circle[i][goLeft(j, 1)] == circle[i][j] ||
					circle[i][goRight(j, 1)] == circle[i][j] ||
					(i > 0 && circle[i - 1][j] == circle[i][j]) ||
					(i < N - 1 && circle[i + 1][j] == circle[i][j]))
				{
					newCircle[i][j] = 0;
					sum -= circle[i][j];
					totCnt--;
					cnt++;
				} else {
					newCircle[i][j] = circle[i][j];
				}
			}
		}
		circle = newCircle;
		
		return cnt;
	}
	
	private static void updown() {
		double avg = (double) sum / totCnt;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (circle[i][j] == 0 || circle[i][j] == avg) {
					continue;
				}
				
				if (circle[i][j] > avg) {
					circle[i][j]--;
					sum--;
				} else {
					circle[i][j]++;
					sum++;
				}
			}
		}
	}
	
	private static int goRight(int idx, int k) {
		int right = idx + k;
		
		if (right < M) {
			return right;
		}
		return right - M;
	}
	
	private static int goLeft(int idx, int k) {
		int left = idx - k;
		
		if (left >= 0) {
			return left;
		}
		return M + left;
	}

}