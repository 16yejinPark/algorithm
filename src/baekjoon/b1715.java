package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//카드 정렬하기
public class b1715 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Long> q = new PriorityQueue<Long>();

		for(int i=0;i<N;i++) {
			q.add(Long.parseLong(br.readLine()));
		}
		
		Long total=0L;
		while(q.size()>1) {
			long a=q.poll();
			long b=q.poll();
			total += a+b;
			q.add(a+b);
		}
		System.out.println(total);
	}
}
