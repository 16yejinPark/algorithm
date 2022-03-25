package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//파일 합치기
public class b11066 {
	//연속된것만 합치기 ㄱㄴ
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for(int t=0;t<T;t++) {
			q.clear();
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int k=0;k<K;k++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			int total = 0;
			while(q.size()>1) {
				int a = q.poll();
				int b = q.poll();
				total += a+b;
				q.add(a+b);
				System.out.printf("%d + %d  total : %d\n",a,b,total);
			}
			System.out.println(total);
		}
	}
}
