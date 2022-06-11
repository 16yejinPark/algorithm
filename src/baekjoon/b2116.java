package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//주사위 쌓기
public class b2116 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		Dice[] dices = new Dice[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			dices[i] = new Dice(a,b,c,d,e,f);
		}
		
		int max = 0;
		for(int i=1;i<=6;i++) {
			int top = i;
			int total = 0;
			for(int d=1;d<=N;d++) {
				char loc = dices[d].findLoc(top);
				int bottom = dices[d].oppsiteSide(loc);
				if(top==6||bottom==6) {
					if(top==5||bottom==5) {
						total += 4;
					}else {
						total += 5;
					}
				}else{
					total += 6;
				}
				top = bottom;
			}
			max = Math.max(max, total);
		}
		System.out.println(max);
	}

	static class Dice{
		int a;//top;
		int b;//left;
		int c;//front;
		int d;//right;
		int e;//back;
		int f;//bottom;
		
		public Dice(int a, int b, int c, int d, int e, int f) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.e = e;
			this.f = f;
		}
		public char findLoc(int n) {
			if(a==n) {
				return 'a';
			}else if(b==n) {
				return 'b';
			}else if(c==n) {
				return 'c';
			}else if(d==n) {
				return 'd';
			}else if(e==n) {
				return 'e';
			}else{
				return 'f';
			}
		}
		public int oppsiteSide(char letter) {
			switch(letter) {
			case 'a': return f;
			case 'b': return d;
			case 'c': return e;
			case 'd': return b;
			case 'e': return c;
			case 'f': return a;
			}
			return 0;
		}
	}

}
