package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//수 찾기
public class b1920 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			int nums[] = new int[N];
			PriorityQueue<Integer> q = new PriorityQueue<Integer>();
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			for(int i=0;i<N;i++) {
				nums[i] = q.poll();
			}
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				int n = Integer.parseInt(st.nextToken());
				int start = 0;
				int end = N-1;
				
				int result = 0;
				while(start <= end) {
					int mid = (start+end)/2;
					//System.out.printf("%d %d %d   %d %d\n",start,mid,end,n,nums[mid]);
					if(nums[mid]==n) {
						result = 1;
						break;
					}else if(nums[mid]<n) {
						start = mid+1;
					}else{
						end = mid-1;
					}
				}
				sb.append(result).append("\n");
			}
		}
		System.out.println(sb.toString());
		
	}
}
