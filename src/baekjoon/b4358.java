package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//생태학
//EOF 처리로 개고생함
public class b4358 {
	static int total=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Trie trie = new Trie();
		String str = "";
		while((str=br.readLine())!=null) {
			trie.insert(str);
			total++;
		}
		print("", trie.root);
	}
	
	static void print(String str,TrieNode node) {
		if(node.isLastChar) {
			StringBuilder sb = new StringBuilder();
			sb.append(str).append(" ").append(String.format("%.4f", node.cnt/(double)total*100));
			System.out.println(sb.toString());
		}
		
		List<Character> mapKey = new ArrayList<>(node.childNodes.keySet());
		Collections.sort(mapKey);

		for(Character key : mapKey) {
			print(str+key,node.childNodes.get(key));
		}
	}
	
	static class Trie{
		TrieNode root = new TrieNode();
		
		void insert(String str) {
			TrieNode node = root;
			for(int i=0;i<str.length();i++) {
				node = node.childNodes.computeIfAbsent(str.charAt(i), tn->new TrieNode());
			}
			node.isLastChar=true;
			node.cnt++;
		}
	}
	
	static class TrieNode{
		Map<Character, TrieNode> childNodes = new HashMap<>();
		boolean isLastChar = false;
		int cnt = 0;
	}
}
