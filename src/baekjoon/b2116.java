package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//주사위 쌓기
public class b2116 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] dices = new int[N][7];
		//int[][] dices_num = new int[N][7];
		
		// 입력받음
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 6; j++) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int n = 0;
		int start=1;
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				n = dices[0][oppositeSide(start++)];
				continue;
			} else {
				for (int j = 1; j <= 6; j++) {
					if(dices[i][j]==n) {
						n = dices[0][oppositeSide(j)];
						break;
					}
				}
			}
		}

		for (int i = 1; i <= 6; i++) {

			for (int j = 1; j < N; j++) {

			}
		}

	}

	public static int oppositeSide(int n) {
		switch (n) {
		case 1:
			return 6;
		case 2:
			return 4;
		case 3:
			return 5;
		case 4:
			return 2;
		case 5:
			return 3;
		case 6:
			return 1;
		}
		return 0;
	}

}
