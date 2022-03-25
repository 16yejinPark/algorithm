package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//최장 증가 부분 수열
public class s3307 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=1;i<=T;i++) {
			int N = Integer.parseInt(br.readLine());
			int LIS[] = new int[N+1];
			st = new StringTokenizer(br.readLine());
			
			int idx = 0;
			for(int j=0;j<N;j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(idx==0) {
					idx++;
					LIS[idx]=temp;
				}else {
					if(LIS[idx]<temp) {
						idx++;
						LIS[idx] = temp;
					}else 
						for(int n=1;n<=idx;n++) 
							if(LIS[n]>=temp) {
								LIS[n]=temp;
								break;
							}
				}
			}
			System.out.printf("#%d %d\n",i,idx);
		}
	}

}
