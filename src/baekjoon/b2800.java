package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

//괄호제거
public class b2800 {
	static ArrayList<Integer> opener = new ArrayList<>();
	static ArrayList<Integer> closer = new ArrayList<>();
	static ArrayList<String> cases = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String original = br.readLine();
		char[] arr = original.toCharArray();
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<arr.length;i++) {
			if(arr[i]=='(') 
				stack.add(i);
			else if(arr[i]==')') {
				int idx = stack.pop();
				opener.add(idx);
				closer.add(i);
			}
		}
		getCases(0,arr);
		if(cases.contains(original))
			cases.remove(original);
		Collections.sort(cases);
		for(String str:cases) {
			System.out.println(str);
		}
	}
	static void getCases(int cnt,char[] arr) {
		if(cnt==opener.size()) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<arr.length;i++) {
				if(arr[i]==' ')
					continue;
				sb.append(arr[i]);
			}
			if(!cases.contains(sb.toString())) {
				cases.add(sb.toString());
			}

			return;
		}
		arr[opener.get(cnt)]=' ';
		arr[closer.get(cnt)]=' ';
		getCases(cnt+1,arr);
		arr[opener.get(cnt)]='(';
		arr[closer.get(cnt)]=')';
		getCases(cnt+1,arr);

	}
}
