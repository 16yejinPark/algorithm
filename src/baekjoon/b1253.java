package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//좋다
public class b1253 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		PriorityQueue<Long> q = new PriorityQueue<Long>();
		for(int i=0;i<N;i++) {
			q.add(Long.parseLong(st.nextToken()));
		}
		long[] nums = new long[N];
		for(int i=0;i<N;i++) {
			nums[i] = q.poll();
		}
		
		long result = 0;
		for(int i=0;i<N;i++) {
			int start = 0;
			int end = N-1;
			//System.out.println(i+"@@@@@@");
			while(start<end) {
				//System.out.printf("%d %d\n",start,end);
				if(nums[start]+nums[end]==nums[i]) {
					if(start==i) {
						start++;
					}else if(end==i) {
						end--;
					}else {
						result++;
						break;
					}
				}else if(nums[start]+nums[end]>nums[i]) {
					end--;
				}else{
					start++;
				}
			}
		}
		System.out.println(result);
	}

}
