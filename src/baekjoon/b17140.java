package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//이차원 배열과 연산
public class b17140 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//(R,C)에 있는 값이 k가 되게 하는 최소 연산
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[100][100];
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int R = 3;
		int C = 3;
		int T = -1;
		for(int t=0;t<=100;t++) {
			if(arr[r-1][c-1]==K) {
				T=t;
				break;
			}
			
			int[][] tempArr = new int[100][100];
			int maxR = 0;
			int maxC = 0;
			if(R>=C) {	//R연산
				for(int i=0;i<R;i++) {
					int[] cnt = new int[101];
					PriorityQueue<int[]> temp = new PriorityQueue<int[]>(new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							if(o1[1]==o2[1])
								return Integer.compare(o1[0],o2[0]);
							return Integer.compare(o1[1],o2[1]);
						}
					});
					for(int j=0;j<C;j++) {
						if(arr[i][j]==0) {
							continue;
						}
						cnt[arr[i][j]]++;
					}
					
					for(int j=1;j<=100;j++) {
						if(cnt[j]>0&&temp.size()<=100) {
							temp.add(new int[] {j,cnt[j]});
						}
					}		
					
					maxC = Math.max(maxC, temp.size()*2);
					int idx=0;
					while(!temp.isEmpty()) {
						int[] nums = temp.remove();
						tempArr[i][idx] = nums[0]; 
						idx++;
						tempArr[i][idx] = nums[1]; 
						idx++;
					}
				}
				C = maxC;
				
			}else {		//C연산
				
				for(int j=0;j<C;j++) {
					int[] cnt = new int[101];
					PriorityQueue<int[]> temp = new PriorityQueue<int[]>(new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							if(o1[1]==o2[1])
								return Integer.compare(o1[0],o2[0]);
							return Integer.compare(o1[1],o2[1]);
						}
					});
					for(int i=0;i<R;i++) {
						if(arr[i][j]==0) {
							continue;
						}
						cnt[arr[i][j]]++;
					}
					
					for(int i=1;i<=100;i++) {
						if(cnt[i]>0&&temp.size()<=100) {
							temp.add(new int[] {i,cnt[i]});
						}
					}			
					maxR = Math.max(maxR, temp.size()*2);
					int idx=0;
					while(!temp.isEmpty()) {
						int[] nums = temp.remove();
						tempArr[idx][j] = nums[0]; 
						idx++;
						tempArr[idx][j] = nums[1]; 
						idx++;
					}
				}
				R = maxR;
			}
			arr = tempArr;	
		}
		System.out.println(T);
	}
}
