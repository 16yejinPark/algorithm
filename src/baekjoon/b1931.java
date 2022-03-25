package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//회의실 배정
public class b1931 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] meetingRoom = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			meetingRoom[i][0] = Integer.parseInt(st.nextToken());
			meetingRoom[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(meetingRoom, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					return Integer.compare(o1[0],o2[0]);
				}
				return Integer.compare(o1[1],o2[1]);
			}
		});
		
		int time=meetingRoom[0][1];
		int cnt=1;
		for(int i=1;i<N;i++) {
			if(meetingRoom[i][0]>=time) {
				cnt++;
				time=meetingRoom[i][1];
			}
		}
		System.out.println(cnt);
	}
}
