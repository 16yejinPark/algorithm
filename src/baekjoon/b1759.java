package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//암호만들기
public class b1759 {
	private static int L;
	private static int C;
	private static String[] alpa;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpa= br.readLine().split(" ");
		Arrays.sort(alpa);
		int[] selected = new int[L];
		makeCombination(selected,0,0);
	}
	
	private static void makeCombination(int[] selected,int idx, int cnt) {
		if(cnt==L) {
			int vcnt=0;
			int ccnt=0;
			StringBuilder sb = new StringBuilder();
			for(int i : selected) {
				if(alpa[i].equals("a")||alpa[i].equals("e")||alpa[i].equals("i")||alpa[i].equals("o")||alpa[i].equals("u")) {
					vcnt++;
				}else {
					ccnt++;
				}
				sb.append(alpa[i]);
			}
			if(vcnt>=1&&ccnt>=2) {
				System.out.println(sb.toString());
			}
			return;
		}
		for(int i=idx;i<C;i++) {
				selected[cnt] = i;
				makeCombination(selected,i+1,cnt+1);
		}
	}

}
