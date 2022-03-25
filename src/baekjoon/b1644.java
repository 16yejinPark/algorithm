package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//소수의 연속합
public class b1644 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int answer = 0;
		if(N==1) { //N이 1이면 그냥 패스 
		}
		else {
			int size = 1;
			int arr[] = new int[N];
			for(int i=2;i<=N;i++) {
				if(isPrime(i)) {
					arr[size++]=i;
				}
			}
			
			
			int front = 0;
			int back = 1;
			int sum = arr[1];
			while(front<=back) {
				if(sum == N) {
					answer++;
				}
				
				if(sum > N) {
					front++;
					sum -= arr[front];
				}else {
					back++;
					if(back>=size) {
						break;
					}else {
						sum += arr[back];
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	static boolean isPrime(int n) {
		if(n==1||n==0) return false;
		for(int i=2;i<=Math.sqrt(n);i++) {
			if(n%i==0) {
				return false;
			}
		}
		return true;
	}
}
