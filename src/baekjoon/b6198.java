package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//옥상 정원 꾸미기
public class b6198 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] height = new int[N];
		for(int i=0;i<N;i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		
		long total = 0;
		for(int i=0;i<N;i++) {
			for(int j=i+1;j<N;j++) {
				if(height[j]<height[i]) {
					total++;
				}
				else if(height[j]>=height[i]) {
					break;
				}
			}
		}
		System.out.println(total);
	}
}
