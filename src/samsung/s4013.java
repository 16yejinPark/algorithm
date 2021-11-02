package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//특이한 자석
public class s4013 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		
		int[] gear = new int[5];
		for (int i = 1; i <= 4; i++) {
			String[] g = br.readLine().split("");
			for (int j = 7; j >= 0; j--) {
				int n = Integer.parseInt(g[j]);
				gear[i] += n * Math.pow(2, j);
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			boolean move[] = new boolean[5];
			move[g] = true;
			int tempG = g;

			while (true) {
				if (tempG + 1 > 4 || !canMove(gear[tempG], gear[tempG + 1])) {
					break;
				}
				move[tempG + 1] = true;
				tempG++;
			}

			tempG = g;
			while (true) {
				if (tempG - 1 <= 0 || !canMove(gear[tempG - 1], gear[tempG])) {
					break;
				}
				move[tempG - 1] = true;
				tempG--;
			}

			for (int j = 1; j <= 4; j++) {
				if (move[j]) {
					if ((j % 2) == (g % 2)) {
						gear[j] = turnGear(gear[j], n);
					} else {
						gear[j] = turnGear(gear[j], n * -1);
					}
				}
			}
		}

		int result = 0;
		for (int i = 1; i <= 4; i++) {
			result += (gear[i] >> 7) * Math.pow(2, i - 1);
		}
		System.out.printf("%d\n", result);

	}

	static boolean canMove(int g1, int g2) {
		if (((g1 >> 5) % 2) == ((g2 >> 1) % 2))
			return false;
		return true;
	}

	static int turnGear(int gear, int clockWise) {
		if (clockWise == 1) { // 시계방향
			if (gear % 2 == 1) {
				gear = gear >> 1;
				gear += 128;
			} else {
				gear = gear >> 1;
			}
		} else { // 반시계방향
			if (gear >= 128) {
				gear -= 128;
				gear = gear << 1;
				gear++;
			} else {
				gear = gear << 1;
			}
		}
		return gear;
	}
}
