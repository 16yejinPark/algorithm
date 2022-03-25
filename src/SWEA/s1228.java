package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//암호문1
public class s1228 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		for (int T = 1; T <= 10; T++) {
			// 원본 암호문의 길이 N ( 10 ≤ N ≤ 20 의 정수),원본 암호문
			List<Integer> list = new LinkedList<Integer>();
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			// 명령어의 개수 ( 5 ≤ N ≤ 10 의 정수), 명령어
			int command = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < command; i++) {
				// I(삽입) x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다. s는 덧붙일 숫자들이다.
				st.nextToken(); //I
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				for(int j=0;j<y;j++) {
					list.add(x, Integer.parseInt(st.nextToken()));
					x++;
				}
			}
			bw.write("#"+T+" ");
			for (int i = 0; i < 10; i++) {
				bw.write(list.get(i)+" ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
}
