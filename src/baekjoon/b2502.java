package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//떡 먹는 호랑이
public class b2502 {
	static int fiboX[];
	static int fiboY[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		fiboX = new int[D+1];
		fiboY = new int[D+1];
		fiboX[1]=1;
		fiboY[2]=1;
		for(int i=3;i<=D;i++) {
			fiboX[i] = fiboX[i-1]+fiboX[i-2];
			fiboY[i] = fiboY[i-1]+fiboY[i-2];
		}
		
		for(int x=1;x<=K;x++) {
			for(int y=1;y<=K;y++) {
				if(((x*fiboX[D])+(y*fiboY[D]))==K) {
					System.out.println(x);
					System.out.println(y);
					return;
				}
			}
		}
	}
}
