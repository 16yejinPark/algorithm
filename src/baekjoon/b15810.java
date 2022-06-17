package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//풍선 공장
public class b15810 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//스태프수
		int M = Integer.parseInt(st.nextToken());	//풍선수
		
		st =new StringTokenizer(br.readLine());
		int staffs[] = new int[N];
		int min = Integer.MAX_VALUE;
		int max = 0;
		for(int i=0;i<N;i++) {
			staffs[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, staffs[i]);
			max = Math.max(max, staffs[i]);
		}
		
		long start = 0;
		long end = (long)max*M;
		while(start<=end) {
			long mid = (start+end)/2;
			System.out.printf("%d %d => %d\n",start,end,mid);
			int total = 0;
			for(int staff:staffs) {
				total += mid/staff;
			}
			if(total<M)
				start = mid+1;
			else if(total>=M)
				end = mid-1;
		}
		
		System.out.println(start);
	}
}
