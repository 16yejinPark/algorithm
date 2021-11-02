package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//소수 & 팰린드롬
public class b1747 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i=N;i<Integer.MAX_VALUE;i++) {
			int end = (int) Math.sqrt(i);
			boolean is_sosu = true;

			for(int j=2;j<=end;j++) {
				if(i%j!=0) {
					continue;
				}
				else {
					is_sosu = false;
					break;
				}
			}

			if(is_sosu) {
				//팰린드롬 검사
				String num = String.valueOf(i);
				if(num.length()==1&&i!=1) {
					System.out.println(num);
					return;
				}
				for(int j=0;j<num.length()/2;j++) {
					if(num.charAt(j)!=num.charAt(num.length()-j-1)) {
						break;
					}
					if(j==num.length()/2-1) {
						System.out.println(num);
						return;
					}
				}
			}
		}
	}

}
