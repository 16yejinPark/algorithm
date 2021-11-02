package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//찾기
//KMP ver.
public class b1786_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();

		//Pi테이블 구하기
		int j=0;
		int[] pi = new int[P.length()];
		//
		for(int i=1;i<P.length();i++) {
			while(j>0&&P.charAt(i)!=P.charAt(j)) {
				j=pi[j-1];
			}
			if(P.charAt(i)==P.charAt(j)) {
				j++;
				pi[i]=j;
			}
		}
		j=0;
		int cnt=0;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<T.length();i++) {
			while(j>0&&T.charAt(i)!= P.charAt(j)) {
				j = pi[j-1];
			}
			if(T.charAt(i)== P.charAt(j)) {
				if(j==P.length()-1) {
					cnt++;
					sb.append(i-j+1).append(" ");
					j=pi[j];
				}
				else j++;
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
