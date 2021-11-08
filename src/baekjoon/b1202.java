package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//보석도둑
public class b1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//보석의 개수
		int K = Integer.parseInt(st.nextToken());	//가방의 개수
		
		//무게가 작은으로 정렬
		PriorityQueue<Jewel> wq = new PriorityQueue<Jewel>((o1,o2)->Integer.compare(o1.w, o2.w));
		//가치가 큰순으로 정렬
		PriorityQueue<Jewel> vq = new PriorityQueue<Jewel>((o1,o2)->Integer.compare(o1.v, o2.v)*(-1));
		
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			wq.add(new Jewel(M,V));
		}
		
		PriorityQueue<Integer> bags = new PriorityQueue<Integer>();
		for(int n=0;n<K;n++) {
			int C = Integer.parseInt(br.readLine());
			bags.add(C);
		}
		
		long total = 0;
		for(int i=0;i<K;i++) {
			int w = bags.poll();
			while(!wq.isEmpty()&&w >= wq.peek().w) {
				Jewel jewel = wq.poll();
				vq.add(jewel);
			}
			if(!vq.isEmpty()) {
				Jewel n = vq.poll();
				total+= n.v;
			}
		}
		System.out.println(total);
		
	}

	static class Jewel{
		int w;
		int v;
		public Jewel(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}
	}
}
