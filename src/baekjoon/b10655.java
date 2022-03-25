package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//마라톤
public class b10655 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] points = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		int maxGap = 0;
		for(int i=0;i<N-2;i++) {
			int gap = getDist(points[i],points[i+1])+getDist(points[i+1],points[i+2])-getDist(points[i],points[i+2]);
			if(maxGap < gap) {
				maxGap = gap;
			}
		}
		int totalDist = 0;
		for(int i=0;i<N-1;i++) {
			totalDist += getDist(points[i],points[i+1]);
		}
		System.out.println(totalDist-maxGap);
	}
	static int getDist(int[] p1,int[] p2) {
		return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
	}
}
