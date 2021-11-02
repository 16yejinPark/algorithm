package ssafy;


import java.io.IOException;

public class PowerTest {

	public static void main(String[] args) throws NumberFormatException, IOException {

		long answer = power(2,10,1000007);
		System.out.println(answer);
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
