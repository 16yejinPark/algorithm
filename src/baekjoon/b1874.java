package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 스택 수열
public class b1874 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<>();

		int[] targets = new int[N];
		int n_idx = 0;
		for (int i = 0; i < N; i++) {
			targets[i] = Integer.parseInt(br.readLine());
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (!s.isEmpty() && targets[n_idx] == s.peek()) {
				while (!s.isEmpty() && targets[n_idx] == s.peek()) {
					int n = s.pop();
					n_idx++;
					sb.append("-\n");
				}
			}
			s.push(i);
			sb.append("+\n");
		}
		
		boolean impossible = false;
		while(!s.isEmpty()) {
			int n = s.pop();
			sb.append("-\n");
			if(targets[n_idx] != n) {
				impossible = true;
				break;
			}
			n_idx++;
		}
		
		if(impossible)
			System.out.println("NO");
		else
			System.out.print(sb.toString());
	}
}
