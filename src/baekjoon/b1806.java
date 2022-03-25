package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//부분합
public class b1806 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int minLen = Integer.MAX_VALUE;
		int sum = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=0;i<N;i++) {	
			int num = Integer.parseInt(st.nextToken());
			q.add(num);
			sum+=num;
			if(sum<S) {
				continue;
			}else {
				while(true) {
					if(!q.isEmpty()&&sum-q.peek()>=S) {
						sum -= q.peek();
						q.remove();
					}else {
						break;
					}
				}
				minLen = Math.min(minLen, q.size());
			}
		}
		System.out.println(minLen!=Integer.MAX_VALUE?minLen:0);
	}
}
