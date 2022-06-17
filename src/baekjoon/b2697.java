package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// 다음수 구하기
public class b2697 {
	static String origin;
	static String prev;
	static int len;
	static int num[];
	static boolean findAnswer;
	static boolean visited[];
	static PriorityQueue<Integer> q = new PriorityQueue<Integer>((o1,o2)->(Integer.compare(o2, o1)));
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			origin = br.readLine();
			prev="";
			findAnswer = false;
			
			String[] temp = origin.split("");
			len = temp.length;
			num = new int[len];
			for(int i=0;i<len;i++) {
				num[i] = Integer.parseInt(temp[i]);
			}	
			Arrays.sort(num);
			visited = new boolean[len];
			perm(0,new StringBuilder());
		}
	}
	
	public static void perm(int n,StringBuilder sb) {	
		if(findAnswer) {
			return;
		}
		if(n==len) {
			String pres = sb.toString();
			//System.out.println(pres);
			if(origin.equals(pres)) {
				if("".equals(prev)) {
					findAnswer = true;
					System.out.println("BIGGEST");
				}else {
					findAnswer = true;
					System.out.println(prev);
				}
			}else {
				prev = sb.toString();
			}
			return;
		}else {
			int before = 0;
			for(int i=len-1;i>=0;i--) {
				if(before == num[i])
					continue;
				if(!visited[i]) {
					visited[i]=true;
					sb.append(num[i]);
					perm(n+1,sb);
					sb.deleteCharAt(n);
					visited[i]=false;
					before = num[i];
				}
			}
		}
	}
}
