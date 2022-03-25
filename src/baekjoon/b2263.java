package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

//트리의 순회
public class b2263 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<Integer,int[]> tree= new HashMap<Integer,int[]>();
		int N = Integer.parseInt(br.readLine());
		
		//inorder
		char[] inorder = br.readLine().toCharArray();
		//postorder
		char[] postorder = br.readLine().toCharArray();
		for(int i=0;i<N;i++) {
			
		}
		
	}
}
