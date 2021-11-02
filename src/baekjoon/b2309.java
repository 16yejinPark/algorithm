package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//일곱난쟁이, 백설공주와 일곱난쟁이
public class b2309{

	private static int[] nan;
	static int sum=0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		nan = new int[9];
		for(int i=0;i<9;i++) {
			nan[i] = Integer.parseInt(br.readLine());
			sum+=nan[i];
		}

		outer:for(int i=0;i<9;i++) {
			for(int j=i+1;j<9;j++) {
				if((sum-nan[i]-nan[j])==100) {
					nan[i]=101;
					nan[j]=101;
					break outer;
				}
			}
		}
		
		Arrays.sort(nan);
		for(int i=0;i<7;i++) {
			System.out.println(nan[i]);
		}

	}

	
}
