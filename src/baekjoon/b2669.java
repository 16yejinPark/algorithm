package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//직사각형 네개의 합집합의 면적 구하기
public class b2669 {
	public static boolean[][] board = new boolean[101][101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		for(int i=0;i<4;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			for(int j=x;j<p;j++) {
				for(int k=y;k<q;k++) {
					board[j][k]=true;
				}
			}
			
		}
		int cnt=0;
		for(int j=0;j<=100;j++) {
			for(int k=0;k<=100;k++) {
				if(board[j][k]==true)
					cnt++;
			}
		}
		System.out.println(cnt);
	}

}
