package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//별 찍기 - 11
public class b2448 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int n = 0;
		int blank = N+5;
		for(int i=1;i<=N;i++) {
			if(blank==-1||(i>=4&&blank==N-1)) {
				blank = (int) (3 * Math.pow(2, n)*2-1);
				n++;
			}
			
			int B = N-i;
			for(int b=0;b<B;b++) {
				System.out.print(" ");
			}
			
			if(i%3==1) {
				while(B<=N*2) {
					B++;
					System.out.print("*");
					B+=blank;
					for(int b=0;b<blank;b++) {
						System.out.print(" ");
					}	
				}
				System.out.println();
			}else if(i%3==2) {
				while(B<=N*2) {
					B+=3;
					System.out.print("* *");
					B+=blank;
					for(int b=0;b<blank;b++) {
						System.out.print(" ");
					}
				}
				System.out.println();
			}else if(i%3==0) {
				while(B<=N*2) {
					B+=5;
					System.out.print("*****");
					B+=blank;
					for(int b=0;b<blank;b++) {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
			blank-=2;
		}
	}

}
