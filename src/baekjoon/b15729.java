package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//방탈출
public class b15729 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean answer[] = new boolean[N];
		for(int i=0;i<N;i++) {	
			int temp = Integer.parseInt(st.nextToken());
			answer[i] = (temp==1);
		}
		
		int cnt = 0;
		boolean lights[] = new boolean[N];

		for(int i=0;i<N;i++) {
			if(lights[i]!=answer[i]) {
				cnt++;
				lights[i] = !lights[i];
				if(i+1<N)
					lights[i+1] = !lights[i+1];
				if(i+2<N)
					lights[i+2] = !lights[i+2];
			}
		}
		System.out.println(cnt);
	}
}
