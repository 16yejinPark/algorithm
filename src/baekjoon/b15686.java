package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class b15686 {
	public static int N;
	public static int M;
	public static int min=-1;
	public static int[] selected;
	public static List<int[]> chicken;
	public static List<int[]> house;
	public static int[][] c_map;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		chicken = new ArrayList<int[]>();
		house = new ArrayList<int[]>();
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp==1) {
					house.add(new int[] {r,c});
				}else if(temp==2) {
					chicken.add(new int[] {r,c});
				}
			}
		}
		
		selected = new int[M];
		getChickenDistance(0,0);
		System.out.println(min);
	}
	private static void getChickenDistance(int cnt, int idx) {
		if(cnt==M) {
			int total_dis = 0;

			for(int i=0;i<house.size();i++) {
				int nearChicken=-1;
				for(int j=0;j<M;j++) {
					if(nearChicken==-1 || nearChicken > Math.abs(house.get(i)[0] - chicken.get(selected[j])[0]) + Math.abs(house.get(i)[1] - chicken.get(selected[j])[1])) {	
						nearChicken = Math.abs(house.get(i)[0] - chicken.get(selected[j])[0]) + Math.abs(house.get(i)[1] - chicken.get(selected[j])[1]);
					}	
				}
				total_dis+=nearChicken;
			}
			
			if(min==-1||min>total_dis) {
				min=total_dis;
			}
			return;
		}
		for(int i=idx;i<chicken.size();i++) {
				selected[cnt] = i;
				getChickenDistance(cnt+1,i+1);
		}
		
	}

}
