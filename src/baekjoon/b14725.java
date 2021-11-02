package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

//개미굴
public class b14725 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		TreeNode root = new TreeNode();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			TreeNode temp = root;
			for(int j=0;j<n;j++) {
				String str= st.nextToken();
				if(!temp.childNode.containsKey(str)) {
					temp.childNode.put(str, new TreeNode());
				}
				temp = temp.childNode.get(str);
			}
		}

		//print
		printTree(root,"");
		System.out.println(sb.toString());
	
	}
	public static void printTree(TreeNode node,String gubun) {
		Object[] keys = node.childNode.keySet().toArray();
		Arrays.sort(keys);
		for(Object key : keys) {
			sb.append(gubun).append(key).append("\n");
			printTree(node.childNode.get(key),gubun+"--");
		}
	}
	public static class TreeNode{
		HashMap<String,TreeNode> childNode = new HashMap<>();
	}

}
