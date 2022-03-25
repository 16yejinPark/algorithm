package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class s5215 {
	public static int[] yum = null;
	public static int[] kcal = null;
	public static int T;
	public static int N;
	public static int L;
	public static int MAX = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			yum = new int[N];
			kcal = new int[N];
			MAX = 0;
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				yum[j] = Integer.parseInt(st.nextToken());
				kcal[j] = Integer.parseInt(st.nextToken());
			}
			hamburgerSet(0, 0,0);
			bw.write("#" + (i + 1) +" "+MAX+"\n");
		}
		bw.flush();
		bw.close();
	}

	public static void hamburgerSet(int n,int total_yum ,int total_kcal) {
		if (total_kcal > L) {
			return;
		}
		if (n == N) {
			if (MAX < total_yum)
				MAX = total_yum;
			return;
		}
		hamburgerSet(n + 1,total_yum+yum[n] ,total_kcal + kcal[n]);
		hamburgerSet(n + 1,total_yum ,total_kcal);
	}

}
