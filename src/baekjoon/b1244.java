package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//스위치 켜고 끄기
public class b1244 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] switches = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=1;i<N+1;i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int std = Integer.parseInt(br.readLine());
		for(int i=0;i<std;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if(gender == 1) {
				for(int idx=1;idx<=N;idx++) {
					if(idx%num==0)
						switches[idx] = (switches[idx]==0?1:0);
				}

			}else if(gender == 2) {
				int temp=1;
				while(num-temp>0&&num+temp<=N&&switches[num-temp]==switches[num+temp]) {
					switches[num+temp] = (switches[num+temp]==0?1:0);
					switches[num-temp] = (switches[num-temp]==0?1:0);
					temp++;
				}
				switches[num] = (switches[num]==0?1:0);
			}
		}
		
		for(int i=1;i<N+1;i++) {
			System.out.printf("%d ",switches[i]);
			if(i%20==0)
				System.out.println("");
		}
	}
}
