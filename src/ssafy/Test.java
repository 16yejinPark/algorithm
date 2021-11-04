package ssafy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Test {
	public static void main(String[] args){
		List<String> list = new ArrayList<String>(); 
		list.add("바계진");
		list.add("바계진");
		list.add("바계진");
		list.add("이남수");
		list.add("김근태");
		list.add("윤설");	
		
		Stream<String> listStream = list.stream();
		listStream.parallel().distinct().filter((str)->(str.length()==3)).forEach(System.out::println);;
	}
}


