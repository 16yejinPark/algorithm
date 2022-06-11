package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//사다리 타기
public class b2469 {
	static int K;
	static int N;
	static int hidden;
	static char[] tobe;
	static char[] result;
	static char[][] ladder;
	static boolean findAnswer = false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		tobe = br.readLine().toCharArray();
		
		hidden = 0;
		ladder = new char[N+1][K];
		for(int i=1;i<=N;i++){
			char[] temp = br.readLine().toCharArray();
			for(int j=1;j<K;j++){
				ladder[i][j] = temp[j-1];
			}
			if(ladder[i][1]=='?') {
				hidden = i;
			}
		}
		
		result = new char[K];
		dfs(1);
		if(!findAnswer) {
			for(int j=1;j<K;j++){
				System.out.print("x");
			}
		}
	}
	
	static void dfs(int n) {
		if(findAnswer) return;
		if(n==K) {
			for(int i=1;i<K;i++) {
				ladder[hidden][i] = result[i];
			}
			if(playGame()) {
				for(int i=1;i<K;i++) {
					System.out.print(result[i]);
				}
				System.out.println();
				findAnswer = true;
			}
			return;
		}
		result[n]='*';
		dfs(n+1);
		
		result[n]='-';
		if(result[n-1]!='-') {
			dfs(n+1);
		}
	}
	
	static boolean playGame() {
		char letter = 'A';
		for(int i=0;i<K;i++) {
			int c = i;
			for(int r=1;r<=N;r++) {
				if(ladder[r][c]=='-') {
					c--;
				}else if(c+1<K&&ladder[r][c+1]=='-') {
					c++;
				}
			}

			if(tobe[c]!=letter) {
				return false;
			}
			letter++;
		}
		return true;
	}

}
