package ssafy;

import java.util.Arrays;

//주사위 3개를 던져서 나올수 있는 경우의 수
//중복 순열이다.
public class RepPer {
	public static int N=6;
	public static int M=3;
	public static int[] num;
	public static void main(String[] args) {
		num = new int[M];
		rep_per(0);
	}
	
	public static void rep_per(int cnt) {
		if(cnt==M) {
			System.out.println(Arrays.toString(num));
			return;
		}
		
		for(int i=1;i<=N;i++) {
			num[cnt] = i;
			rep_per(cnt+1);
		}
	}

}
