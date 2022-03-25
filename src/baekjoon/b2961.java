package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

//도영이가 만든 맛있는 음식
public class b2961 {
	public static int[] sour;
	public static int[] bitter;
	public static boolean[] selected;
	public static long gap;
	public static int N;
//	public static BigInteger max = new BigInteger("999999999");
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		sour = new int[N];
		bitter = new int[N];
		selected = new boolean[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}
		gap = Math.abs(sour[0]-bitter[0]);
		selectIngr(0);
		System.out.println(gap);
		
	}
	
	public static void selectIngr(int cnt) {
		if(cnt==N) {
			//공집합 제거
			boolean zeroSubSet = true;
			int mul_s=1;
			long sum_b=0;
			for(int i=0;i<N;i++) {
				if(selected[i]) {
//					BigInteger temp_m = new BigInteger(Integer.toString(mul_s));
//					BigInteger temp_s = new BigInteger(Integer.toString(sour[i]));
//					if(temp_m.multiply(temp_s).compareTo(max)>=0)
//						return;
					mul_s *= sour[i];
					sum_b += bitter[i];
					zeroSubSet=false;
				}
			}
			
			if(zeroSubSet) return;
			if(gap > Math.abs(mul_s-sum_b)) {
				gap = Math.abs(mul_s-sum_b);
			}
			return;
		}
		
		selected[cnt]=true;
		selectIngr(cnt+1);
		
		selected[cnt]=false;
		selectIngr(cnt+1);
		
	}
}
