package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class s1225 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int i=0;i<10;i++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Queue<Integer> q = new LinkedList<>();
			for(int j=0;j<8;j++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			int num=1;
			while(q.peek()-num>0) {
				q.add(q.poll()-num);
				if(num==5) {
					num=1;
				}else {
					num++;
				}
			}
			q.remove();
			q.add(0);
			
			System.out.print("#"+n);
			while(!q.isEmpty()) {
				System.out.print(" "+q.poll());
			}
			System.out.print("\n");
		}

	}

}
