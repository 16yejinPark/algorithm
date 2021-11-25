
import java.util.*;
import java.lang.Math;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Main {
	
	static int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	
	public static int solution(String[] ledgers) {
		Stack<int[]> s = new Stack<>();
		int total = 0;
		for(int i=0;i<ledgers.length;i++) {
			String[] deal = ledgers[i].split(" ");
			String[] date = deal[0].split("/");
			int month = Integer.parseInt(date[0]);
			int day = Integer.parseInt(date[1]);
			int rate = Integer.parseInt(deal[1]);
			int amount = Integer.parseInt(deal[2]);
			
			//입금
			if(amount>0) {
				s.add(new int[] {month,day,rate,amount});
			}else {	//출금
				amount *= -1;
				while(amount > 0) {
					System.out.println("amount: " +amount);
					int temp[] = s.pop();
					int bf_month = temp[0];
					int bf_day = temp[1];
					int bf_rate = temp[2];
					int bf_amount = temp[3];
					if(bf_amount > amount) {
						total += getInterest(amount, bf_rate, getDateDiff(bf_month, bf_day,month,day));
						s.push(new int[] {bf_month,bf_day,bf_rate,bf_amount-amount});
						amount = 0;
					}else {
						total += getInterest(bf_amount, bf_rate, getDateDiff(bf_month, bf_day,month,day));
						amount -= bf_amount;
					}
					
				}
			}
			
		}
		
		while(!s.isEmpty()) {
			int temp[] = s.pop();
			int bf_month = temp[0];
			int bf_day = temp[1];
			int bf_rate = temp[2];
			int bf_amount = temp[3];
			System.out.println(getInterest(bf_amount, bf_rate, getDateDiff(bf_month, bf_day,12,31)));
			total += getInterest(bf_amount, bf_rate, getDateDiff(bf_month, bf_day,12,31));
		}
		
		
		return total;
	}
	
	public static int getInterest(int amount, int rate, int period) {
		return (int)((amount * rate/ 100.0)*(period/365.0));
	}
	
	public static int getDateDiff(int m1,int d1,int m2,int d2) {
		if(m1==m2) {
			return d2-d1;
		}
		int result = days[m1]-d1;
		m1++;
		while(m1<m2) {
			result+=days[m1];
			m1++;
		}
		result+= d2;
		return result;
	}
	
	public static void main(String[] args) throws ParseException{
		//System.out.println(getInterest(3555,6,21));
		//System.out.println(getInterest(10000,4,72));
	//System.out.println(getInterest(10000,4,getDateDiff(8,31,11,11)));
		//System.out.println(getDateDiff(8,31,11,11));
		
		String[] input = {"04/01 1 40000", "05/01 5 20000","08/31 4 10000","11/11 0 -45000"};
		System.out.println(solution(input));
	}

}
