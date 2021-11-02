package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//사다리 조작
public class b15684 {
	static int ladder[][];
	static int N;
	static int M;
	static int H;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//세로선 개수
		M = Integer.parseInt(st.nextToken());	//가로선 개수
		H = Integer.parseInt(st.nextToken());	//세로선마다 가로선을 놓을 수 있는 위치
		
	}
		
}
