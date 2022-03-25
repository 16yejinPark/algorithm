package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//이항계수3
public class b11401 {
	static long[] dp = new long[4000001];
	static final int mod = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long bunza = factorial(N);
		long bunmo = factorial(K)*factorial(N-K)%mod;
		long answer = (bunza * power(bunmo,mod-2,mod))%mod;
		System.out.println(answer);
		
	}
	
	static long factorial(int n) {
		if(n==0) {
			return 1;
		}else if(n==1) {
			return 1;
		}else if(dp[n]==0) {
			dp[n] = (factorial(n-1)*n)%mod;
		}
		return dp[n];
	}
	
	
	static long power(long x, long y, long p) {
		long res = 1L;
		while(y>0) {
			if(y%2==1) {
				res = (res*x)%p;
			}
			y=y>>1;
			x=(x*x)%p;
		}
		return res;
	}
}
