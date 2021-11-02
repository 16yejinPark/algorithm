package samsung;

import java.util.Scanner;

public class p1954 {

	public static void main(String[] args) {

		// → ↓ ← ↑
		int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		sc.nextLine();
		
		for (int i = 0; i < T; i++) {
			int cnt = 1;
			int size = sc.nextInt();
			int rep = size-1;
			int x=0; int y=0;
			System.out.printf("#%d\n",i+1);
			int[][] snail = new int[size][size];
			
			while(rep>=0) {
				if(rep==0) {
					snail[size/2][size/2] = cnt;
					break;
				}
				for(int j=0;j<4;j++) {
					for(int k=0;k<rep;k++) {
						snail[y][x] = cnt;
						x = x + deltas[j][1];
						y = y + deltas[j][0];
						cnt++;
					}
				}
				x++;
				y++;
				rep = rep-2;
			}
			for(int j=0;j<size;j++) {
				for(int k=0;k<size;k++) {
					System.out.print(snail[j][k] + " ");
				}
				System.out.println("");
			}
			sc.nextLine();
		}
		sc.close();	
	}

}
