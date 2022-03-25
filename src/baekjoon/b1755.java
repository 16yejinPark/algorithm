package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//숫자 놀이
public class b1755 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Integer[] nums = new Integer[N-M+1];
		for(int i=0;i<=N-M;i++) {
			nums[i]=M+i;
		}
		

		int[] order = {10,5,9,8,3,2,7,6,1,4};	//0~9까지의 가중치를 담은 배열

		//정렬
		Arrays.sort(nums, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int ten1 = o1/10;
				int ten2 = o2/10;
				int one1 = o1%10;
				int one2 = o2%10;
				
				if(ten1==ten2) {	//10의 자리가 동일하면 1의자리 기준으로 정렬
					return Integer.compare(order[one1],order[one2]);
				}else if(ten1==0) {	//둘 중 하나가 한자리 수면, 1의자리와 10의 자리 비교
					return Integer.compare(order[one1],order[ten2]);
				}else if(ten2==0) {	
					return Integer.compare(order[ten1],order[one2]);
				}else {				//둘 다 두자리 수면 십의 자리 비교
					return Integer.compare(order[ten1],order[ten2]);
				}
			}
		});
		
		//출력
		int cnt=0;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<=N-M;i++) {
			if(cnt!=0&&cnt%10==0) {
				sb.append("\n");
			}
			sb.append(nums[i]).append(" ");
			cnt++;
		}
		System.out.println(sb.toString());
		
	}

}