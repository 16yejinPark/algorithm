package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class b16637 {
	static int N;
	static String expression[];
	static long max = Long.MIN_VALUE;
	//괄호 추가하기
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		expression = br.readLine().split("");
		dfs(2,Long.parseLong(expression[0]));
		System.out.println(max);
	}
	
	static void dfs(int n,long result) {
		if(n>=N) {
			max = Math.max(max, result);
			return;
		}else {
			char oper1 = expression[n-1].charAt(0);
			long a = Long.parseLong(expression[n]);
			
			//안묶
			long r1 = calculate(result,oper1,a);
			dfs(n+2,r1);
			
			//묶
			if(n+2<N) {
				char oper2 = expression[n+1].charAt(0);
				long b = Long.parseLong(expression[n+2]);
				long r2 = calculate(a,oper2,b);
				long r3 = calculate(result,oper1,r2);
				dfs(n+4,r3);
			}
		}
	}

	static long calculate(long a, char oper,long b) {
		long result = 0;
		switch(oper) {
		case '+': result = a + b; break;
		case '-': result = a - b; break;
		case '*': result = a * b; break;
		}
		return result;
	}
}
