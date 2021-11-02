package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//파리퇴치
public class s2001 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int[][] map = new int[size][size];
			for(int j=0;j<size;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<size;k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max=0;
			for(int j=0;j<(size-n+1);j++) {
				for(int k=0;k<(size-n+1);k++) {
					int sum=0;
					for(int l=j;l<(j+n);l++) {
						for(int m=k;m<(k+n);m++) {
							sum+=map[l][m];
						}
					}
					if(max < sum) {
						max=sum;
					}
				}
			}
			System.out.printf("#%d %d\n",i+1,max);
		}
	}

}
