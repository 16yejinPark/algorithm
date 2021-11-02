package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//컨베이어 벨트 위의 로봇
public class b20055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Integer> belt = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<2*N;i++) {
			belt.add(Integer.parseInt(st.nextToken()));
		}
		boolean[] robots = new boolean[N];
		int result = 1;
		run:while(true) {
			//1
			int temp1 = belt.remove(belt.size()-1);
			belt.add(temp1,0);
			boolean temp2 = robots[2*N-1];
			for(int i=0;i<N-1;i++) {
				robots[i+1] = robots[i];
			}
			
			//2
			for(int i=N-1;i>0;i--) {
				if(i==N-1&&robots[N-1]) {
					robots[N-1] = false;
				}
				if(!robots[i+1]&&belt.get(i+1)>=1) {
					robots[i]=false;
					robots[i+1]=true;
					int negu = belt.get(i+1);
					belt.set(i+1,negu-1);
					if(i+1==N-1) {
						robots[N-1] = false;
					}
				}
			}
			
			//3
			int negu = belt.get(0);
			if(negu!=0) {
				robots[0]=true;
				belt.set(0,negu-1);
			}
			
			//4
			int zero = 0;
			for(int i=0;i<2*N;i++) {
				if(belt.get(i)==0) {
					zero++;
					if(zero==K)
						break run;
				}
			}
			result++;
		}
		System.out.println(result);
	}
}
