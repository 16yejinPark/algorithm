package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//색종이 붙이기
public class b17136 {
	static int map[][] = new int[10][10];
	static int[] pNum = { 0, 5, 5, 5, 5, 5 };
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0, 0);
		System.out.println(result==Integer.MAX_VALUE?-1:result);
	}

	public static void dfs(int r, int c,int cnt) {
		System.out.printf("%d %d %d\n",r,c,cnt);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j]+" ");
			}System.out.println();
		}
		for(int i : pNum) {
			System.out.print(i+" ");
		}System.out.println("min => "+result+"\n");
		
		
		if (checkPaper()) {
			result = Math.min(result, cnt);
			return;
		} else {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (map[i][j] == 1) {
						int[][] clone = new int[10][10];
						clonePaper(map, clone);
						
						for(int k=5;k>=1;k--) {
							if (pNum[k] > 0) {
								boolean changeAll = true;
								outer:for (int x = i; x < i+k; x++) {
									for (int y = j; y < j+k; y++) {
										if(x>=0&&x<10&&y>=0&&y<10&&map[x][y]==1) {
											map[x][y]=0;
										}else {
											changeAll = false;
											break outer;
										}
										
									}
								}
								if(changeAll) {
									pNum[k]--;
									dfs(i,j,cnt+1);
									clonePaper(clone,map);
									pNum[k]++;
								}else {
									clonePaper(clone,map);
								}
							}
						}
					}
				}
			}
		}
	}

	private static boolean checkPaper() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	private static void clonePaper(int[][] from, int[][] to) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
}
