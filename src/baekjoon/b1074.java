package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Z
public class b1074 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		
		int size = (int)Math.pow(2, N);
		int cr = 0;
		int cc = 0;
		int num  =0;
		while(true) {
			if(cr==r&&cc==c) {
				break;
			}
			
			if(r<(size/2+cr)) {
				//2번
				if(c>=(size/2+cc)){
					num += Math.pow(size/2, 2);
					cc+=size/2;
				}
			}else {
				//3번
				if(c<(size/2+cc)) {
					num += Math.pow(size/2, 2)*2;
					cr+=size/2;
				}
				//4번
				else {
					num += Math.pow(size/2, 2)*3;
					cr+=size/2;
					cc+=size/2;
				}
			}			
			size = size/2;
		}
		
		System.out.println(num);
		
	}

}
