package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 용돈 관리
public class b6236 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<N;i++) {
			int money = Integer.parseInt(br.readLine());
			list.add(money);
		}
		
		while(list.size()!=M) {
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)->(Integer.compare(o1[1],o2[1])));
			for(int i=0;i<list.size()-1;i++) {
				int a = list.get(i);
				int b = list.get(i+1);
				pq.add(new int[] {i,a+b});
			}
			int[] e = pq.remove();
			list.remove(e[0]);
			list.remove(e[0]);
			list.add(e[0], e[1]);
		}
		
		int max = 0;
		for(int i:list) {
			max = Math.max(max, i);
		}
		System.out.println(max);
	}

}
