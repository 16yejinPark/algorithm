package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//내려가기
public class b2096 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][3];
		int[][] minMemo = new int[N][3];
		int[][] maxMemo = new int[N][3];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minMemo[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<3;j++) {
				if(i==0) {
					minMemo[i][j]=map[i][j];
					maxMemo[i][j]=map[i][j];
				}else {
					if(j-1>=0) {
						minMemo[i][j] = Math.min(minMemo[i][j], minMemo[i-1][j-1]+map[i][j]);
						maxMemo[i][j] = Math.max(maxMemo[i][j], maxMemo[i-1][j-1]+map[i][j]);
					}
					if(j+1<3) {
						minMemo[i][j] = Math.min(minMemo[i][j], minMemo[i-1][j+1]+map[i][j]);
						maxMemo[i][j] = Math.max(maxMemo[i][j], maxMemo[i-1][j+1]+map[i][j]);
					}
					minMemo[i][j] = Math.min(minMemo[i][j], minMemo[i-1][j]+map[i][j]);
					maxMemo[i][j] = Math.max(maxMemo[i][j], maxMemo[i-1][j]+map[i][j]);
				}
			}		
		}
		
		int min = Integer.MAX_VALUE;
		int max = 0;
		for(int j=0;j<3;j++) {
			min = Math.min(minMemo[N-1][j], min);
			max = Math.max(maxMemo[N-1][j], max);
		}
		System.out.println(max+" "+min);
	}
}
