package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//이중 우선순위 큐
public class b7662 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int K = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((o1,o2)->(Integer.compare(o1, o2)));
			PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1,o2)->(Integer.compare(o1, o2)*(-1)));
			Map<Integer,Integer> valid = new HashMap<Integer,Integer>();
			for(int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine());
				char op = st.nextToken().charAt(0);
				Integer n = Integer.parseInt(st.nextToken());
				
				if(op=='I') {
					maxHeap.add(n);
					minHeap.add(n);
					valid.put(n, valid.getOrDefault(n, 0)+1);
				}else if(op=='D') {
					if(n==1&&!maxHeap.isEmpty()) {
						while(!maxHeap.isEmpty()) {
							int nn = maxHeap.poll();
							if(valid.get(nn)>0) {
								valid.put(nn, valid.get(nn)-1);
								break;
							}
						}
					}else if(n==-1&&!minHeap.isEmpty()) {
						while(!minHeap.isEmpty()) {
							int nn = minHeap.poll();
							if(valid.get(nn)>0) {
								valid.put(nn, valid.get(nn)-1);
								break;
							}
						}
					}
				}
			}
			
			double min = Integer.MAX_VALUE+10;
			while(!minHeap.isEmpty()) {
				int nn = minHeap.poll();
				if(valid.get(nn)>0) {
					min = nn;
					break;
				}
			}
			
			double max = Integer.MIN_VALUE-10;
			while(!maxHeap.isEmpty()) {
				int nn = maxHeap.poll();
				if(valid.get(nn)>0) {
					max = nn;
					break;
				}
			}

			if(max==Integer.MIN_VALUE-10) {
				System.out.println("EMPTY");
			}else {
				System.out.printf("%d %d\n",(int)max,(int)min);
			}	
		}
	}
}
