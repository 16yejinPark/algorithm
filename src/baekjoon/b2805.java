package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//나무 자르기
public class b2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//나무의 수
		long M = Long.parseLong(st.nextToken());	//집으로 가져가려고 하는 나무의 길이
		
		long[] trees = new long[N];
		PriorityQueue<Long> q = new PriorityQueue<Long>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			q.add(Long.parseLong(st.nextToken()));
		}
		for(int i=0;i<N;i++) {
			trees[i] = q.poll();
		}
		
		long start = 0;
		long end = trees[N-1];
		long len = 0;
		while(start <= end) {
			long total = 0L;
			long mid = (end + start)/2;
			
			for(int i=0;i<N;i++) {
				if(trees[i]-mid>0) {
					total += trees[i]-mid;
				}
			}	
			
			if(total >= M) {
				start = mid +1;
				len = Math.max(len, mid);
			}else {
				end = mid -1;
			}
		}
		System.out.println(len);
	}

}
