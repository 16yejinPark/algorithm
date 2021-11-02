package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b11066 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[][] memo = new int[K+1][K+1];
			int[] data = new int[K+1];
			for(int k=1;k<=K;k++) {
				data[k] = Integer.parseInt(st.nextToken());
				memo[k][k] = data[k];
			}
			
			int r=1;
			int c=2;
			int startC=2;
			while(true) {
//				System.out.println();
//				for(int i=1;i<=K;i++) {
//					for(int j=1;j<=K;j++) {
//						System.out.printf("%3d ",memo[i][j]);
//					}
//					System.out.println();
//				}
				memo[r][c] = Math.min(memo[r+1][c]+data[r],memo[r][c-1]+data[c]);
				//System.out.printf("%d %d\n",memo[r+1][c],data[r-1]);
				if(r==1&&c==K) {
					break;
				}
				if(c==K) {
					startC++;
					r=1;
					c=startC;
					continue;
				}
				r++;c++;
			}
			
			System.out.println();
			for(int i=1;i<=K;i++) {
				System.out.printf("%3d ",data[i]);
			}
			System.out.println();
			for(int i=1;i<=K;i++) {
				for(int j=1;j<=K;j++) {
					System.out.printf("%3d ",memo[i][j]);
				}
				System.out.println();
			}
			System.out.println(memo[1][K]);
		}

	}

}
