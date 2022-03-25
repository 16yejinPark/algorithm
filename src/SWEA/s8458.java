package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s8458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			boolean is_even=true;
			int maxDist=-1;
			for(int n=0; n<N;n++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dist = Math.abs(x)+Math.abs(y);
				if(n==0) {
					is_even = (dist%2==0);
				}else if((dist%2==0)!=is_even) {
					maxDist=-1;
					break;
				}
				maxDist = Math.max(maxDist, dist);
			}
			System.out.printf("#%d %d\n",t,maxDist);
		}
	}

}
