package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//공유기 설치
public class b2110 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//집의 개수
		int C = Integer.parseInt(st.nextToken());	//공유기 개수
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for(int i=0;i<N;i++) {
			q.add(Integer.parseInt(br.readLine()));
		}
		
		int houses[] = new int[N];
		for(int i=0;i<N;i++) {
			houses[i] = q.poll();
		}
		
		int start = 1;
		int end = houses[N-1] - houses[0];
		int max = 0;
		while(start <= end) {
			int gap = (start + end) / 2;
			int cnt=1;
			int prev = houses[0];
			for(int i=1;i<N;i++) {
				if(houses[i]-prev>=gap) {
					cnt++;
					prev = houses[i];
				}
			}
			//System.out.printf("%d %d %d => %d\n",start,gap,end,cnt);
			if(cnt >= C) {
				start = gap+ 1;
				max = Math.max(max, gap);
			}else {
				end = gap - 1;
			}
		}
		
		System.out.println(max);
	}

}
