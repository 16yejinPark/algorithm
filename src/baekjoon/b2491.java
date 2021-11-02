package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//
public class b2491 {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] asc = new int[N];
		int[] desc = new int[N];
		int beforeNum = Integer.parseInt(st.nextToken());
		asc[0]=1; desc[0]=1;
		for(int i=1;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num < beforeNum) {
				desc[i] = desc[i-1]+1;
				asc[i] = 1;
			}else if(num > beforeNum) {
				asc[i] = asc[i-1]+1;
				desc[i] = 1;
			}else if(num == beforeNum) {
				asc[i] = asc[i-1]+1;
				desc[i] = desc[i-1]+1;
			}
			beforeNum = num;
		}
		
		int max=0;
		for(int i=0;i<N;i++) {
			if(max<asc[i])
				max=asc[i];
			if(max<desc[i])
				max=desc[i];
		}
		
		System.out.println(max);
	}

}
