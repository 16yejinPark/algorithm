package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

//정육점
public class b2258_answer {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//덩어리 개수
		int M = Integer.parseInt(st.nextToken());	//필요한 고기의 양
		
		int[][] arr = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());	//무게
			int P = Integer.parseInt(st.nextToken());	//가격
			arr[i] = new int[]{W,P};
		}
		
		Arrays.sort(arr, (o1,o2)->{
			if(o1[1]==o2[1])
				return Integer.compare(o2[0],o1[0]);
			return Integer.compare(o1[1],o2[1]);
		});
		
		int same = 0;
		int wsum = 0;
		int answer = Integer.MAX_VALUE;
		boolean isPossible = false;
		for(int i = 0; i < N; i++) {
			int weight = arr[i][0];
			int price = arr[i][1];
			
			wsum += weight;
			
			if(i > 0 && arr[i - 1][1] == arr[i][1]) {
				same += price;
			}else {
				same = price;
			}
			
			if(wsum>=M) {
				isPossible = true;
				answer = Integer.min(answer, same);
			}
		}
		System.out.println(isPossible ? answer + "\n" : -1 + "\n");
	}
}
