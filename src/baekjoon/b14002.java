package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//가장 긴 증가하는 부분 수열4
public class b14002 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N+1];
		int[] dp = new int[N+1];
		int[] tracking = new int[N+1];
		
		for(int i=0;i<=N;i++) {
			tracking[i]=-1;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			nums[i]=Integer.parseInt(st.nextToken());
		}
		
		int len = 0;
		int maxIdx = -1;
		for(int i=1;i<=N;i++) {
			if(nums[dp[len]]<nums[i]) {
				tracking[i]=dp[len];
				dp[++len]=i;
				maxIdx=i;
			}else {
				for(int j=1;j<=len;j++) {
					if(nums[dp[j]]>=nums[i]) {
						tracking[i]=dp[j-1];
						dp[j]=i;
						break;
					}
				}
			}
//			for(int x=1;x<=N;x++) {
//				System.out.print(tracking[x]+" ");
//			}System.out.println();
		}
		
		System.out.println(len);
		int idx = maxIdx;
		StringBuilder sb = new StringBuilder();
		while(tracking[idx]!=-1){
			sb.insert(0, nums[idx]+" ");
			idx = tracking[idx];
		}
		System.out.println(sb.toString());
	}
}
