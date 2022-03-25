package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s6806 {
	public static int[] kyuyung;
	public static int[] inyung;
	public static boolean[] card;
	public static int[] remainCard;
	public static boolean[] used;
	public static int win;
	public static int lose;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t =1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			card = new boolean[19];
			remainCard = new int[9];
			used = new boolean[9];
			kyuyung = new int[9];
			inyung = new int[9];
			win=0;
			lose=0;
			for(int i=0;i<9;i++) {
				
				kyuyung[i] = Integer.parseInt(st.nextToken());
				card[kyuyung[i]] = true;
			}
			int idx = 0;
			for(int i=1;i<=18;i++) {
				if(!card[i]) {
					remainCard[idx] = i;
					idx++;
				}
			}
			
			playGame(0);
			
			System.out.printf("#%d %d %d\n",t,win,lose);
		}
	}
	
	public static void playGame(int cnt) {
		if(cnt==9) {
			int score_K=0;
			int score_I=0;		
			
			for(int i=0;i<9;i++) {
				if(kyuyung[i]>inyung[i])
					score_K+=(kyuyung[i]+inyung[i]);
				else if(kyuyung[i]<inyung[i])
					score_I+=(kyuyung[i]+inyung[i]);
			}
			if(score_K>score_I)
				win++;
			else if(score_K<score_I)
				lose++;
			return;
		}
		for(int card=0;card<9;card++) {
			if(!used[card]) {
				used[card] = true;
				inyung[cnt] = remainCard[card];
				playGame(cnt+1);
				used[card] = false;
			}
		}
	}

}
