package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class PerfectShuffle {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int pos = N/2;
			String[] str1 = new String[N-pos];
			String[] str2 = new String[pos];
		
			for(int j=0;j<N;j++) {		
				String temp = st.nextToken();
				if(j<N-pos) {
					str1[j]= temp;
				}
				else {
					str2[j-(N-pos)]=temp;
				}
			}	

			System.out.printf("#%d ",i+1);
			for(int j=0;j<str1.length;j++) {
				System.out.printf("%s ",str1[j]);
				if(j<str2.length) {
					System.out.printf("%s ",str2[j]);
				}
			}
			System.out.printf("\n");
		}
	}
}
