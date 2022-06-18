package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 포켓볼
public class b4030 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//31622=루트 10^9
		ArrayList<Integer> list = new ArrayList<>();
		for(int m=2;m<=31622;m++) {

			long n = (m*m-1);

			long start = 0;
			long end = n*2;
			while(start<=end) {
				long mid = (start+end)/2;
				if((mid)*(mid+1)<n*2) {
					start = mid+1;
				}else {
					end = mid-1;
				}
			}

			if((start*(start+1))==n*2) {
				list.add((int) n+1);
			}
		}
			
		int no = 1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(A==0&&B==0)break;
			
			int cnt = 0;
			for(int i=0;i<list.size();i++) {				
				int num = list.get(i);
				if(A<num&&num<B)
					cnt++;
			}
			System.out.printf("Case %d: %d\n",no,cnt);
			no++;
		}
	}

}
