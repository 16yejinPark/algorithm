package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//다음 수 구하기
public class b2697_greedy {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			String[] number = br.readLine().split("");
			int size = number.length;
			if(size==1) {
				System.out.println("BIGGEST");
				continue;
			}
			
			int idx = 0;
			int cnt[] = new int[10];
			cnt[Integer.parseInt(number[size-1])]++;
			for(int i=size-2;i>=0;i--) {
				int n1 = Integer.parseInt(number[i]);
				int n2 = Integer.parseInt(number[i+1]);
				cnt[n1]++;
				if(n1 < n2) {
					idx = i;
					break;
				}
			}
			
			if(idx==0) {
				System.out.println("BIGGEST");
				continue;
			}
			
			int n = Integer.parseInt(number[idx]);
			for(int i=n+1;i<10;i++) {
				if(cnt[i]>0) {
					number[idx] = String.valueOf(i);
					cnt[i]--;
					break;
				}
			}
			
			for(int i=idx+1;i<size;i++) {
				for(int j=0;j<10;j++) {
					if(cnt[j]>0) {
						number[i] = String.valueOf(j);
						cnt[j]--;
						break;
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<size;i++) {
				sb.append(number[i]);
			}
			System.out.println(sb.toString());
		}

	}

}
