package ssafy;

import java.util.Arrays;

//주사위 3개를 던져서 나올수 있는 경우의 수
//중복 조합이다.	//nHm = n+m-1Cm   //ex)6H3 = 8C3 
public class RepCom {
	public static int N=6;
	public static int M=3;
	public static int[] num;
	public static void main(String[] args) {
		num = new int[M];
		rep_com(0,1);
	}
	
	public static void rep_com(int cnt,int start) {
		if(cnt==M) {
			System.out.println(Arrays.toString(num));
			return;
		}
		
		for(int i=start;i<=N;i++) {
			num[cnt] = i;
			rep_com(cnt+1,i);
		}
	}

}
