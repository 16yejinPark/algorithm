package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

//가운데를 말해요
public class b1655 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minpq = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxpq = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i=1;i<=N;i++){
			int n = Integer.parseInt(br.readLine());

			if(minpq.size()<maxpq.size()) {
				minpq.add(n);
			}else if(minpq.size()==maxpq.size()) {
				maxpq.add(n);
			}	
			
			while(!maxpq.isEmpty()&&!minpq.isEmpty()&&maxpq.peek()>minpq.peek()) {
				int max = maxpq.poll();
				int min = minpq.poll();
				maxpq.add(min);
				minpq.add(max);
			}
			sb.append(maxpq.peek()).append("\n");
		}
		System.out.println(sb.toString());
	}
}
