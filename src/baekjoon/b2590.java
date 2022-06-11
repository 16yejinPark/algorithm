package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//색종이
public class b2590 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int one = Integer.parseInt(br.readLine());
		int two = Integer.parseInt(br.readLine());
		int tre = Integer.parseInt(br.readLine());
		int fur = Integer.parseInt(br.readLine());
		int fiv = Integer.parseInt(br.readLine());
		int six = Integer.parseInt(br.readLine());
		
		int sum = 0;
		
		//6
		sum += (six);
		
		//5
		sum += (fiv);
		one -= (fiv*11);
		if(one<0) one=0;
		
		//4
		sum += (fur);	
		int rest = fur*5;
		if(rest <= two) {
			two -= rest;
		}else {
			rest -= two;
			two = 0;
			one -= rest*4;
			if(one<0) one=0;
		}
		
		//3
		sum += (tre/4);
		tre %= 4;
		if(tre>0) {
			sum++;
			if(tre==3) {
				if(two>0) {
					two--;
					one-=5;
					if(one<0) one=0;
				}else {
					one-=9;
					if(one<0) one=0;
				}
			}else if(tre==2) {
				if(two<3) {
					rest = 3-two;
					two=0;
					one-=(rest*4+6);
					if(one<0) one=0;
				}else {
					two-=3;
					one-=6;
					if(one<0) one=0;
				}
			}else if(tre==1) {
				if(two<5) {
					rest = 5-two;
					two=0;
					one-=(rest*4+7);
					if(one<0) one=0;
				}else {
					two-=5;
					one-=7;
					if(one<0) one=0;
				}
			}
		}
		
		//2
		sum += (two/9);
		two %= 9;
		if(two>0) {
			sum++;
			one -= (36 - (two*4));
			if(one<0) one=0;
		}
		
		//1
		sum += (one/36);
		one %= 36;
		if(one>0) {
			sum++;
		}	
		
		System.out.println(sum);
	}
}
