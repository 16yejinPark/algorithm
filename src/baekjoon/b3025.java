package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b3025 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int R = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		char[][] map = new char[R][C];
		
		//입력
		for (int r = 0; r < R; r++) {
			String[] temp2 = br.readLine().split("");
			for (int c = 0; c < C; c++) {
				map[r][c] = temp2[c].charAt(0);
			}
		}

		//경우의 수만큼 반복
		int N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			int current_r = 0;
			int current_c = Integer.parseInt(br.readLine()) - 1;
			while (true) {
				//System.out.printf("%d %d\n", current_r, current_c);
				// 땅(가장 아랫줄)이라면 화산탄은 그 자리에 멈춰 굳게 된다.
				if (current_r == R - 1) {
					map[current_r][current_c] = 'O';
					break;
				}
				// 화산탄의 아래칸이 장애물로 막혀 있으면 그 자리에 멈춰 굳게 된다.
				else if (map[current_r + 1][current_c] == 'X') {
					map[current_r][current_c] = 'O';
					break;
				}
				// 화산탄의 아래칸이 비어 있다면, 화산탄은 아래 칸으로 굴러 떨어지게 된다
				else if (map[current_r + 1][current_c] == '.') {
					current_r++;
					continue;
				}
				// 화산탄의 아래칸에 굳은 화산탄이 있다.
				else if (map[current_r + 1][current_c] == 'O') {
					//만약 화산탄의 왼쪽 칸과 왼쪽-아래칸이 비어 있다면, 화산탄은 왼쪽-아래칸으로 굴러 떨어진다.
					if (current_c - 1 >= 0) {
						if (map[current_r][current_c - 1] == '.' && map[current_r + 1][current_c - 1] == '.') {
							current_r++;
							current_c--;
							continue;
						}
					}
					//만약 왼쪽으로 굴러 떨어지지 않았다면, 오른쪽 칸과 오른쪽-아래 칸이 비어 있다면, 화산탄은 오른쪽-아래칸으로 굴러 떨어진다.
					if (current_c + 1 < C) {
						if (map[current_r][current_c + 1] == '.' && map[current_r + 1][current_c + 1] == '.') {
							current_r++;
							current_c++;
							continue;
						}
					}
					//위의 두경우가 아니라면 화산탄은 자리에 멈추고 굳어 다시는 움직이지 않는다.
					map[current_r][current_c] = 'O';
					break;
				}
			}
		}
		//출력
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}
}
