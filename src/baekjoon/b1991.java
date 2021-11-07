package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//트리 순회
public class b1991 {
	static HashMap<Character,char[]> tree = new HashMap<Character,char[]>();
	static char r;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			char[] sibling = {left, right};
			tree.putIfAbsent(root, sibling);
			if(i==1) {
				r = root;
			}
		}
		
		prefix(r);System.out.println();
		infix(r);System.out.println();
		postfix(r);System.out.println();
		
		
	}
	
	static void prefix(char letter) {
		System.out.print(letter);
		char[] sibling = tree.get(letter);
		if(sibling[0]!='.') {
			prefix(sibling[0]);
		}
		if(sibling[1]!='.') {
			prefix(sibling[1]);
		}
	}
	
	static void infix(char letter) {
		char[] sibling = tree.get(letter);
		if(sibling[0]!='.') {
			infix(sibling[0]);
		}
		System.out.print(letter);
		if(sibling[1]!='.') {
			infix(sibling[1]);
		}	
	}
	
	static void postfix(char letter) {
		char[] sibling = tree.get(letter);
		if(sibling[0]!='.') {
			postfix(sibling[0]);
		}
		if(sibling[1]!='.') {
			postfix(sibling[1]);
		}	
		System.out.print(letter);
	}
	
}
