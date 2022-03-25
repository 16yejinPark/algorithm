package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//곱셈
public class b1629 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		System.out.println(power(A,B,C));
	}
	static long power(long x,long y, long p) {
		long res = 1L;
		x = x%p;
		while(y>0) {
			if(y%2==1) {
				res = (res*x)%p;
			}
			y = y >> 1;
			x = (x*x)%p;
		}
		return res;
	}
}
