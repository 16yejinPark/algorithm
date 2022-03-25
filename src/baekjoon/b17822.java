package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//원판 돌리기
public class b17822 {
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//disk 수
		int M = Integer.parseInt(st.nextToken());	//disk 위의 숫자의 수
		int T = Integer.parseInt(st.nextToken());
		
		Disk[] disks = new Disk[N+1];
		for(int i=1;i<=N;i++) {
			Disk disk = new Disk();
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				int m = Integer.parseInt(st.nextToken()); 
				disk.addNum(m);
			}
			disks[i] = disk;
		}
		
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());	//X의 배수인 Disk를 대상으로
			int D = Integer.parseInt(st.nextToken());	//해당 방향으로
			int K = Integer.parseInt(st.nextToken());	//해당 칸만큼 이동
			
			//돌리기
			for(int i=1;i*X<=N;i++) {
				for(int j=0;j<K;j++) {
					disks[i*X].turnNums(D);
				}
			}
			
			//인접한 수 제거
			boolean changed = false;
			Disk[] copy = new Disk[N+1];
			
			//한 원판에서의 비교
			for(int i=1;i<=N;i++) {
				Disk disk = new Disk();
				for(int j=0;j<M;j++) {
					int out = disks[i].nums.removeFirst();
					if(out!=0&&(out==disks[i].nums.peekFirst()||out==disks[i].nums.peekLast())) {
						changed = true;
						disk.nums.addLast(0);
					}else {
						disk.nums.addLast(out);
					}
					disks[i].nums.addLast(out);
				}
				copy[i] = disk;
			}
			
			//다른 원판과 비교
			for(int i=1;i<=M;i++) {
				ArrayDeque<Integer> cmp = new ArrayDeque<>();
				ArrayDeque<Integer> temp = new ArrayDeque<>();
				for(int j=1;j<=N;j++) {
					int out1 = disks[j].nums.removeFirst();
					int out2 = copy[j].nums.removeFirst();
					cmp.addLast(out1);
					temp.addLast(out2);
				}
				
				for(int j=1;j<=N;j++) {
					int out1 = cmp.removeFirst();
					int out2 = temp.removeFirst();
					
					if(out1==0) {
						copy[j].nums.addLast(out2);
						cmp.addLast(out1);
						continue;
					}
					
					if(j==1) {
						if(out1 == cmp.peekFirst()) {
							changed = true;
							copy[j].nums.addLast(0);
						}else {
							copy[j].nums.addLast(out2);
						}
					}else if(j==N){
						if(out1 == cmp.peekLast()) {
							changed = true;
							copy[j].nums.addLast(0);	
						}else {
							copy[j].nums.addLast(out2);
						}
					}else {
						if(out1 == cmp.peekFirst()||out1 == cmp.peekLast()) {
							changed = true;
							copy[j].nums.addLast(0);
						}else {
							copy[j].nums.addLast(out2);
						}
					}
					cmp.addLast(out1);
				}
			}
			disks = copy;

			if(!changed) {
				double avg = sum(disks,N,M)/(double)cnt;
				for(int i=1;i<=N;i++) {
					Disk disk = disks[i];
					for(int j=1;j<=M;j++) {
						int out = disk.nums.removeFirst();
						if(out!=0&&out!=avg) {
							if(out>avg) {
								disk.nums.addLast(out-1);
							}else if(out<avg){
								disk.nums.addLast(out+1);
							}
						}else {
							disk.nums.addLast(out);
						}
					}
				}
			}
		}
		System.out.println(sum(disks,N,M));
	}
	
	static int sum(Disk[] disks,int N,int M) {
		cnt = 0;
		int sum = 0;
		for(int i=1;i<=N;i++) {
			Disk disk = disks[i];
			for(int j=1;j<=M;j++) {
				int out = disk.nums.removeFirst();
				if(out!=0)cnt++;
				sum += out;
				disk.nums.addLast(out);
			}
		}
		return sum;
	}
	
	static void print(Disk[] disks,int N,int M) {
		for(int i=1;i<=N;i++) {
			Disk disk = disks[i];
			for(int j=0;j<disk.nums.size();j++) {
				int out = disk.nums.removeFirst();
				System.out.print(out+" ");
				disk.nums.addLast(out);
			}
			System.out.println();
		}
		System.out.println();
	}
	static class Disk{
		ArrayDeque<Integer> nums;
		public Disk() {
			this.nums = new ArrayDeque<Integer>();
		}
		
		public void addNum(Integer num) {
			nums.addLast(num);
		}
		
		public void turnNums(int type) {
			int out;
			switch(type) {
			case 0:	//시계
				out = nums.removeLast();
				nums.addFirst(out);
				break;
			case 1:	//반시계
				out = nums.removeFirst();
				nums.addLast(out);
				break;
			}
		}	
	}
}
