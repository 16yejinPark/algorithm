package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//사람 네트워크2
public class s1263 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] network = new int[N+1][N+1];	
			for(int r=1;r<=N;r++) {
				for(int c=1;c<=N;c++) {
						int temp = Integer.parseInt(st.nextToken());
						if(r!=c&&temp==0)temp=10000;
						network[r][c] = temp;
					}
			}
		
			
			for(int via=1;via<=N;via++) {
				for(int r=1;r<=N;r++) {
					for(int c=1;c<=N;c++) {
						network[r][c]=Math.min(network[r][c],network[r][via]+network[via][c]);
					}
				}
			}
			int min = Integer.MAX_VALUE;
			for(int r=1;r<=N;r++) {
				int total=0;
				for(int c=1;c<=N;c++) {
					total+=network[r][c];
				}
				min = Math.min(min, total);
			}
			System.out.printf("#%d %d\n",t,min);
		}

	}

}
