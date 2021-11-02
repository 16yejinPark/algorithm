package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//하노이 탑
public class b11729 {
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int disk = Integer.parseInt(bf.readLine());
		move(disk,1,2,3);
		System.out.println(cnt);
		System.out.println(sb);
	}
	public static void move(int disk,int start, int temp, int dstn) {
		if(disk==0) return;
		cnt++;
		move(disk-1,start,dstn,temp);
		sb.append(start+" "+dstn+"\n");
		move(disk-1,temp,start,dstn);
	}
	
}
