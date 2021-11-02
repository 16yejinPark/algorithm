package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//신입사원
public class b1946 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			int[] score = new int[N+1];
			for(int n=0;n<N;n++) {
				String[] temp = br.readLine().split(" ");
				score[Integer.parseInt(temp[0])]=Integer.parseInt(temp[1]);
			}
			int cnt=N;
			for(int n=2;n<=N;n++) {
				if(score[1]<score[n]) {
					cnt--;
				}else {
					score[1]=score[n];
				}
			}
			System.out.println(cnt);
		}
	}

}
