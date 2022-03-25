package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//농작물 수확하기
public class s2805 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0;i<T;i++) {
			int sum = 0;
			int size = Integer.parseInt(br.readLine());
			for(int j=0;j<size;j++) {
				String str = br.readLine();
				if((size/2)>=j) {
					int idx=(size/2)-j;
					int cnt=j*2+1;
					while(cnt!=0) {
						sum += Character.getNumericValue(str.charAt(idx)) ;
						idx++;
						cnt--;
					}
				}else if((size/2)<j) {
					int idx=j-(size/2);
					int cnt=size-(2*(j-(size/2)));
					while(cnt!=0) {
						sum += Character.getNumericValue(str.charAt(idx)) ;
						idx++;
						cnt--;
					}
				}
			}
			System.out.printf("#%d %d\n",i+1,sum);
		}
	}

}
