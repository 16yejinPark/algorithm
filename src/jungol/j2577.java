package JungOl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

//회전초밥(고)
public class j2577 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//접시의 수
		int D = Integer.parseInt(st.nextToken());	//초밥의 가짓수
		int K = Integer.parseInt(st.nextToken());	//연속해서 먹는 접시의 수
		int C = Integer.parseInt(st.nextToken());	//쿠폰 번호
		int[] dishes = new int[N];
		for(int i=0;i<N;i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}
		
		int maxDish = 0;
		for(int i=0;i<N;i++) {
			HashSet<Integer> set = new HashSet<Integer>();
			for(int k=0;k<K;k++) {
				set.add(dishes[(i+k)%N]);
			}
			set.add(C);
			maxDish = Math.max(maxDish, set.size());
			if(maxDish==D) {
				break;
			}
		}
		System.out.println(maxDish);
	}
}
