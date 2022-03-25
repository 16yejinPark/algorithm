package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Flatten {
	static int[] boxes = new int[100];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int ans=0;
		for(int i=0;i<10;i++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<100;j++) {
				boxes[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j=0;j<n;j++) {
				int max = boxes[0];
				int min = boxes[0];
				int maxIdx = 0;
				int minIdx = 0;
				for(int k=0;k<100;k++) {
					if(max<boxes[k]) {
						max=boxes[k];
						maxIdx = k;
					}
						
					if(min>boxes[k]) {
						min=boxes[k];
						minIdx = k;
					}
				}
				boxes[maxIdx]--;
				boxes[minIdx]++;
			}
			
			int max = boxes[0];
			int min = boxes[0];
			for(int k=0;k<100;k++) {
				if(max<boxes[k]) {
					max=boxes[k];
				}
					
				if(min>boxes[k]) {
					min=boxes[k];
				}
			}
			ans = max - min;
			System.out.printf("#%d %d\n",i+1,ans);
			
		}
	}
}
