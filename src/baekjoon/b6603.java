package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//로또
public class b6603 {
	static int K;
	static int[] nums;
	static int[] selected = new int[6];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());

			if (K != 0) {
				nums = new int[K];
				for (int i = 0; i < K; i++) {
					nums[i] = Integer.parseInt(st.nextToken());
				}
				combi(0, 0);
				System.out.println();
			}

		} while (K != 0);
	}

	public static void combi(int cnt, int idx) {
		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				System.out.print(selected[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = idx; i < K; i++) {
			selected[cnt] = nums[i];
			combi(cnt + 1, i + 1);
		}

	}

}
