package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//연료 채우기
public class b1826 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());	//주유소의 개수
		
		PriorityQueue<int[]> distQ = new PriorityQueue<int[]>((o1,o2)->(Integer.compare(o1[0],o2[0])));
		PriorityQueue<int[]> fuelQ = new PriorityQueue<int[]>((o1,o2)->((-1)*Integer.compare(o1[1],o2[1])));
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	//주유소까지의 거리
			int b = Integer.parseInt(st.nextToken());	//주유소에서 채울 수 있는 연료의 양
			distQ.add(new int[] {a,b});
		}
		
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());	//마을까지의 거리
		int P = Integer.parseInt(st.nextToken());	//트럭에 원래 있던 연료
		
		int cnt = 0;
		while(P<L) {
			//갈 수 있는 거리에 있는 주유소들
			if(distQ.isEmpty()) {
				cnt=-1;
				break;
			}
			while(!distQ.isEmpty()&&P>=distQ.peek()[0]) {
				fuelQ.add(distQ.poll()); 
			}
			if(fuelQ.isEmpty()) {
				cnt=-1;
				break;
			}
			int[] gasStation = fuelQ.poll();
			P+=gasStation[1];
			cnt++;
		}
		System.out.println(cnt);
	}
}
