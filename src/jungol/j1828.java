package JungOl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Food implements Comparable<Food>{
	public int low;
	public int high;
	public Food() {}
	public Food(int low, int high) {
		super();
		this.low = low;
		this.high = high;
	}
	@Override
	public int compareTo(Food o) {
		if(this.low == o.low)
			return this.high - o.high;
		return this.low - o.low;
	}
}

public class j1828 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		Food[] arr = new Food[N];
		Stack<Food> ref = new Stack<Food>();;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int low = Integer.parseInt(st.nextToken());
			int high = Integer.parseInt(st.nextToken());  
			arr[i] =new Food(low,high);
		}
		Arrays.sort(arr);
		ref.push(arr[0]);
		for(int i=1;i<N;i++) {
			boolean contain = false;
			Food f = ref.pop();

			if(f.low<=arr[i].low&&f.high>=arr[i].low) {
				f.low = arr[i].low;
				contain = true;
			}
				
			if(f.high>=arr[i].high&&f.low<=arr[i].high) {
				f.high = arr[i].high;
				contain = true;
			}

			ref.push(f);
			if(!contain) {
				ref.push(arr[i]);
			}
		}
		System.out.println(ref.size());
		
	}
}
