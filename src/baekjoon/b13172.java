package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시그마
public class b13172 {
	static final int mod = 1000000007;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		long sum = 0L;
		for(int m=0;m<M;m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			sum = (sum + (power(N,mod-2,mod)*S)%mod)%mod;
		}
		System.out.println(sum);
	}
	static long power(long a, long b, int p) {
		long res = 1L;
		a=a%p;
		while(b>0) {
			if(b%2==1) {
				res=(res*a)%p;
			}
			b = b >> 1;
			a = (a*a)%p;
		}
		return res;
	}
}
