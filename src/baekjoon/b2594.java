package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//놀이공원
public class b2594 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->(Integer.compare(o1[0], o2[0])));
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int s = convertTime(Integer.parseInt(st.nextToken()),"s");
			int e = convertTime(Integer.parseInt(st.nextToken()),"e");
			q.add(new int[] {s,e});
		}
		
		int answer = 0;
		int start = 600;
		while(!q.isEmpty()) {
			int[] temp = q.remove();
			int s = temp[0];
			int e = temp[1];;
			answer = Math.max(s-start, answer);
			
			start = Math.max(start,e);
		}
		answer = Math.max(1320-start, answer);
		System.out.println(answer);
	}
	
	public static int convertTime(int time,String type) {
		int h = time/100;
		int m = time%100;
		if(type.equals("s"))
			return Math.max(h*60+m-10, 600);
		else if(type.equals("e"))
			return Math.min(h*60+m+10, 1320);
		return 0;
	}
}
