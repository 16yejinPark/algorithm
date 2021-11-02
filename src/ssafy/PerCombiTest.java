package ssafy;

import java.util.Arrays;

public class PerCombiTest {

	/*
	순열: 순서의 의미가 있다
	예) 1 2 3 (N:2)
	1  2
	1  3
	2  1
	2  3
	3  1
	3  2

	조합: 순서의 의미가 없다
	예) 1 2 3  (N:2)
	1  2
	1  3
	2  3
	*/
	static int[] num = {1,2,3};
	static int N=3;
	static int sCount=1;
	//순열 
	private static void makePermutation(int cnt,int[] selected,boolean[] visited) {
		if(cnt==N) {
			System.out.println(Arrays.toString(selected));
		}
		for(int i=0;i<num.length;i++) {
			if(!visited[i]) {
				visited[i]=true;
				selected[cnt] = num[i];
				makePermutation(cnt+1,selected,visited);
				visited[i]=false;
			}
		}
	}
	
	//조합 
	private static void makeCombination(int cnt,int idx,int[] selected) {
		if(cnt==N) {
			System.out.println(Arrays.toString(selected));
		}
		for(int i=idx;i<num.length;i++) {
				selected[cnt] = num[i];
				makeCombination(cnt+1,i+1,selected);
		}
	}
	
	//부분집합
	private static void powerSet(int cnt,boolean[] selected) {
		if(cnt==N) {
			sCount++;
			System.out.print("{");
			for(int i=0;i<N;i++) {
				if(selected[i]) {
					System.out.print(num[i]+" ");
				}
			}
			System.out.print("}, ");
			return;
		}
		//선택
		selected[cnt]=true;
		powerSet(cnt+1,selected);
		//비선택
		selected[cnt]=false;
		powerSet(cnt+1,selected);
	}
	
	public static void main(String[] args) {
		//  1. num에서 N개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
		makePermutation(0,new int[N],new boolean[num.length]);
		
		//  2. num에서 N개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
		makeCombination(0,0,new int[N]);
		
		//  3. num으로 구성할 수 있는 모든 부분집합을 출력하시오.
		powerSet(0,new boolean[N]);
		System.out.println("\n총 경우의수: "+sCount);
	}

}
