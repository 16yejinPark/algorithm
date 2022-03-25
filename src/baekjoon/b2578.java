package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//빙고
public class b2578 {
	public static int[][] board = new int[5][5];
	public static int[] vertical = new int[5];
	public static int[] horizontal = new int[5];
	public static int[] diagonal = new int[2];
	public static int bingoCnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int i=0;i<5;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		outer:for(int i=0;i<5;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				cnt++;
				cntBingo(Integer.parseInt(st.nextToken()));
				if(bingoCnt>=3) {
					break outer;
				}
			}
		}
		
		System.out.println(cnt);
		
	}
	
	public static void cntBingo(int n) {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(board[i][j]==n) {
					//왼위대각 확인
					if(i==j) {
						diagonal[0]++;
						if(diagonal[0]==5)
							bingoCnt++;
					}
					//오위대각 확인
					if(i+j==4) {
						diagonal[1]++;
						if(diagonal[1]==5)
							bingoCnt++;
					}
					//세로 확인
					vertical[i]++;
					if(vertical[i]==5)
						bingoCnt++;
					//가로 확인
					horizontal[j]++;
					if(horizontal[j]==5)
						bingoCnt++;
					return;
				}
			}
		}
	}
	
}
