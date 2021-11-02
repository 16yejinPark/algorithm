package ssafy;

import java.util.Arrays;

/*nPr ==> 3P2 = 3 * 2 = 6
-----순열-----
[1, 2]
[1, 3]
[2, 1]
[2, 3]
[3, 1]
[3, 2]
-----조합-----
[1, 2]
[1, 3]
[2, 3]
----부분집합----
{1,2}
{1}
{2}
{}
총 부분집합의 수:4
*/
public class CombeTest {
	public static int N = 3;
	public static int R = 2;
	static int[] num = {1,2};
	public static void main(String[] args) {
//		1. num에서 N개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
		System.out.println("-----순열-----");
		makePermutation(0, new int[R], new boolean[N+1]);
		
//		2. num에서 N개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.		
		System.out.println("-----조합-----");
		makeCombination(0, new int[R], 1);

//		3. num으로 구성할 수 있는 모든 부분집합을 출력하시오.
		System.out.println("----부분집합----");
		powerSet(0, new boolean[N]);
//		System.out.println("총 부분집합의 수:" + sCount);
	}
	public static void makePermutation(int cnt,int[] selected,boolean[] visited) {
		if(cnt==R) {
			System.out.println(Arrays.toString(selected));
			return;
		}
		for(int i=1;i<=N;i++) {
			if(visited[i])continue;
			selected[cnt] = i;
			visited[i]=true;
			makePermutation(cnt+1,selected,visited);
			visited[i]=false;	
		}
	}
	
	public static void makeCombination(int cnt,int[] selected,int start) {
		if(cnt==R) {
			System.out.println(Arrays.toString(selected));
			return;
		}
		for(int i=start;i<=N;i++) {
			selected[cnt] = i;
			makeCombination(cnt+1,selected,i+1);
		}
	}
	
	public static void powerSet(int cnt,boolean[] visited) {
		if(cnt==R) {
			System.out.print("{");
			for(int i=0;i<visited.length;i++) {
				if(visited[i]) {
					System.out.print(num[i]+" ");
				}
			}
			System.out.print("}, ");
			return;
		}
		
		visited[cnt] = true;
		powerSet(cnt+1,visited);
		
		visited[cnt] = false;
		powerSet(cnt+1,visited);
	}
	
}	
