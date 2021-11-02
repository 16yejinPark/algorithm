package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


//오큰수!
public class b17298 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] answer = new int[N];
		int[] list = new int[N];
		
		Stack<Integer> s = new Stack<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<N;i++) {
			answer[i]=-1;
		}
		
		s.push(0);
		for(int i=1;i<N;i++) {
			while(!s.empty() && list[s.peek()] < list[i]) {
				answer[s.pop()] = list[i];
			}
			s.push(i);
		}
		
		
		for(int i=0;i<N;i++) {
			System.out.printf("%d ",answer[i]);
		}
	}
}
