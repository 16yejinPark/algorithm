package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 프로세서 연결하기
public class s1767 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=1;i<=T;i++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int r=0;r<N;r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c]=Integer.parseInt(st.nextToken());
				}
			}
			
			
			System.out.printf("#%d %d",i,i);
		}

	}
}
