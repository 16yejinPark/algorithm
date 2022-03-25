package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//리모컨
public class b1107 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());	//수빈이가 이동하려는 채널
		int M = Integer.parseInt(br.readLine());	//고장난 버튼의 수
		
		
		HashMap<Integer,Boolean> btn = new HashMap<Integer,Boolean>();
		for(int i=0;i<=9;i++) {
			btn.put(i, true);
		}
		if(M>0) {
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				int n = Integer.parseInt(st.nextToken());
				btn.put(n, false);
			}
		}

		int cnt = Math.abs(N-100);	
		for(int i=0;i<=999999;i++) {
			boolean possible = true;
			int tempCnt = 0;
			int n = i;
			do {
				tempCnt++;
				int r = n%10;
				if(!btn.get(r)) {
					possible = false;
					break;
				}
				n /= 10;
			}while(n!=0);
			if(possible) {
				cnt = Math.min(cnt, tempCnt+Math.abs(N-i));
			}
		}
		System.out.println(cnt);
		
	}
}
