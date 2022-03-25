package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//전깃줄
public class b2565 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] wire = new int[N][2];
		int[] dp = new int[N+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			wire[i][0] = Integer.parseInt(st.nextToken());
			wire[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(wire,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		
		int len=0;
		for(int i=0;i<N;i++) {
			if(dp[len]<wire[i][1]) {
				dp[++len]=wire[i][1];
			}else {
				for(int j=1;j<=len;j++) {
					if(dp[j]>wire[i][1]) {
						dp[j]=wire[i][1];
						break;
					}
				}
			}
		}
		System.out.println(N-len);
	}

}
