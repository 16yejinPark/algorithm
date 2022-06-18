package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b6236_binarySearch {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int sum = 0;
		int max = 0;
		for(int i=0;i<N;i++) {
			int money = Integer.parseInt(br.readLine());
			arr[i]=money;
			sum += money;
			max = Math.max(arr[i], max);
		}
		
		int start= max;
		int end = sum;
		while(start <= end) {
			int mid = (start+end)/2;
			int result = run(arr,mid);
			if(result>M) {
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
		System.out.println(start);
	}
	
	public static int run(int[] arr,int money) {
		int cnt = 0;
		int remain = 0;
		for(int i=0;i<arr.length;i++) {
			if(remain-arr[i]<0) {
				cnt++;
				remain=money;
			}
			remain-=arr[i];
		}
		//System.out.println(money+" "+cnt);
		return cnt;
	}
}
