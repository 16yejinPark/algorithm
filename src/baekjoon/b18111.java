package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b18111 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[N][M];
		int min_h = 300;
		int max_h = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min_h = Math.min(min_h, map[i][j]);
				max_h = Math.max(max_h, map[i][j]);
			}
		}
		
		int min_time = Integer.MAX_VALUE;
		int height = 0;
		for(int h=max_h;h>=min_h;h--) {
			int b_temp = B;
			int time = 0;
			boolean stop = false;
			outer:for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					int temp = Math.abs(map[i][j]-h);
					if(map[i][j]<h) {
						time += temp;
						b_temp -= temp;
						if(min_time<time) {
							break outer;
						}
					}else if(map[i][j]>h) {
						time += (temp*2);
						b_temp += temp;
						if(min_time<time) {
							break outer;
						}
					}
				}
			}
			if(b_temp>=0 && min_time>time) {
				min_time=time;
				height = h;
			}
		}
		System.out.printf("%d %d\n",min_time,height);
	}
}
