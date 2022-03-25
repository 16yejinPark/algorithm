package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//찾기
//아마..라빈 카프 알고리즘
public class b1786 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();

		int hashNum = getFirstHash(T,P.length());
		int targetHash = getFirstHash(P,P.length());

		int cnt = 0;
		int i=1;
		StringBuilder sb = new StringBuilder();
		if(hashNum==targetHash) {
			doubleCheck(T,P,0);
			cnt++;
			sb.append(1).append(" ");
		}
		
		while(i<T.length()-P.length()+1) {
			int nextHash = getHash(T,hashNum,i,P.length());
			hashNum = nextHash;
			if(hashNum==targetHash) {
				doubleCheck(T,P,i);
				cnt++;
				sb.append(i+1).append(" ");
			}		
			i++;
		}
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	public static boolean doubleCheck(String T, String P, int idx) {
		int targetIdx = 0;
		for(int i=idx;i<P.length()+idx;i++) {
			if(T.charAt(idx)!=P.charAt(targetIdx))
				return false;
			targetIdx++;
		}
		return true;
	}
	
	public static int getFirstHash(String T, int length) {
		int total = 0;
		for(int i=0;i<length;i++) {
			if(i<T.length()) {
				total = (total+(int) (T.charAt(i)*(Math.pow(2,length-i-1)%Integer.MAX_VALUE)%Integer.MAX_VALUE)%Integer.MAX_VALUE);
			}
		}
		return total;
	}
	
	public static int getHash(String T, int hashNum, int start, int length) {
		//sliding window 기법?
		int N = (int) (hashNum - (((T.charAt(start-1)*(Math.pow(2,length-1)))%Integer.MAX_VALUE)));
		N = (N>0?N:Integer.MAX_VALUE-N);
		N = (N*2)%Integer.MAX_VALUE;
		N = (N +T.charAt(start+length-1)%Integer.MAX_VALUE);
		return N;
	}
}
