package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//탑
public class b2493 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Integer> s = new Stack<>();
		int[] tower = new int[n+1];
		int[] result = new int[n+1];
		for(int i=1;i<=n;i++) {
			tower[i]=Integer.parseInt(st.nextToken());
		}
		
		s.push(5);
		for(int i=n;i>0;i--) {
			if(s.empty()) {
				s.push(i);
				continue;
			}
			while(!s.empty()&&tower[s.peek()]<tower[i]) {
				result[s.pop()] = i;
			}
			s.push(i);
		}
		
		for(int i=1;i<=n;i++) {
			System.out.print(result[i]+" ");
		}
	}

}
