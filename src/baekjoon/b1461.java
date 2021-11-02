package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//도서관
public class b1461 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> pBooks = new ArrayList<Integer>();
		ArrayList<Integer> nBooks = new ArrayList<Integer>();
		for(int i=0;i<N;i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(temp>0) {
				pBooks.add(temp);
			}else {
				nBooks.add(temp);
			}
		}
		Collections.sort(pBooks,Collections.reverseOrder());
		Collections.sort(nBooks);
		int maxDist=0;
		int totalDist=0;
		while(nBooks.size()!=0) {
			int dist = Math.abs(nBooks.remove(0));
			maxDist = Math.max(maxDist, dist);
			totalDist += dist;
			for(int i=0;i<M-1;i++) {
				if(nBooks.size()>0) {
					nBooks.remove(0);
				}
			}
		}
		
		while(pBooks.size()!=0) {
			int dist = Math.abs(pBooks.remove(0));
			maxDist = Math.max(maxDist, dist);
			totalDist += dist;
			for(int i=0;i<M-1;i++) {
				if(pBooks.size()>0) {
					pBooks.remove(0);
				}
			}
		}
		System.out.println(totalDist*2-maxDist);
	}
}
