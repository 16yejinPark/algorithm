package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class b17822_2try {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		Circle[] circles = new Circle[N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			circles[i] = new Circle(M);
			for(int j=1;j<=M;j++) {
				int n = Integer.parseInt(st.nextToken());
				circles[i].list.addLast(n);
			}
		}
		
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());	//X번 원판
			int D = Integer.parseInt(st.nextToken());	//D방향(0은 시계 1은 반시계)
			int K = Integer.parseInt(st.nextToken());	//K칸 회전
			
			int x = X;
			while(x<=N) {
				circles[x].turn(D, K);
				x+=X;
			}

			//temp 생성
			Circle[] temp = new Circle[N+1];
			for(int i=1;i<=N;i++) {
				temp[i] = new Circle(M);
			}
			
			boolean changed = false;
			//같은 원판에서 같으면서 인접하는 수
			for(int i=1;i<=N;i++) {
				int prev = circles[i].list.removeFirst();
				boolean lastZero = false;
				if(prev!=0&&prev == circles[i].list.peekLast()) {
					temp[i].list.addLast(0);
					lastZero = true;
					changed = true;
				}else {
					temp[i].list.addLast(prev);
				}
				circles[i].list.addLast(prev);
				for(int j=2;j<=M;j++) {
					int pres = circles[i].list.removeFirst();
					if(prev!=0&&prev==pres) {
						changed = true;
						int n = temp[i].list.removeLast();
						temp[i].list.addLast(0);
						temp[i].list.addLast(0);
					}else {
						temp[i].list.addLast(pres);
					}
					circles[i].list.addLast(pres);
					prev = pres;
				}
				if(lastZero) {
					changed = true;
					temp[i].list.removeLast();
					temp[i].list.addLast(0);
				}
			}
			
			//다른 원판에서 같으면서 인접하는 수
			for(int j=1;j<=M;j++) {
				int prev = circles[1].list.removeFirst();
				for(int i=2;i<=N;i++) {
					int pres = circles[i].list.removeFirst();
					if(prev!=0&&prev==pres) {
						changed = true;
						temp[i-1].removeByIdx(j);
						temp[i].removeByIdx(j);
					}
					prev = pres;
				}
			}
			circles = temp;
			if(!changed) {
				int sum = 0;
				int cnt = 0;
				for(int i=1;i<=N;i++) {
					cnt+=circles[i].cnt();
					sum+=circles[i].sum();
				}
				double avg = (double)sum / cnt;
				for(int i=1;i<=N;i++) {
					for(int j=1;j<=M;j++) {
						int n = circles[i].list.removeFirst();
						if(n==0||n==avg) {
							circles[i].list.addLast(n);
						}else {
							if(n>avg) {
								circles[i].list.addLast(n-1);
							}else if(n<avg) {
								circles[i].list.addLast(n+1);
							}
						}
					}
				}
			}
		}
		
		int sum = 0;
		for(int i=1;i<=N;i++) {
			sum+=circles[i].sum();
		}
		System.out.println(sum);
		
	}

	static class Circle{
		int size;
		ArrayDeque<Integer> list;
		public Circle(int M) {
			super();
			size = M;
			list = new ArrayDeque<Integer>();
		}
		public void turn(int d,int k) {
			if(d==0) {
				for(int i=0;i<k;i++) {
					int n = list.removeLast();
					list.addFirst(n);
				}
			}else {
				for(int i=0;i<k;i++) {
					int n = list.removeFirst();
					list.addLast(n);
				}
			}
		}
		public void removeByIdx(int idx) {
			for(int i=1;i<=size;i++) {
				int n = list.removeFirst();
				if(i==idx) {
					list.addLast(0);
				}else {
					list.addLast(n);
				}
			}
		}
		public int sum() {
			int total = 0;
			for(int i=1;i<=size;i++) {
				int n = list.removeFirst();
				total+=n;
				list.addLast(n);
			}
			return total;
		}
		public int cnt() {
			int cnt = 0;
			for(int i=1;i<=size;i++) {
				int n = list.removeFirst();
				if(n!=0)cnt++;
				list.addLast(n);
			}
			return cnt;
		}
	}
}
