package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main2 {
	public static void main(String[] args) throws IOException {
		
		int[] alpha = new int[] { 9, 4, 8, 7, 2, 1, 6, 5, 0, 3 }; // 알파벳에 따른 우선순위
		
		for(int aaa=1;aaa<=99;aaa++) {
			for(int bbb=aaa;bbb<=99;bbb++) {
				StringBuilder sb = new StringBuilder();
			      int mNum = aaa;
			      int nNum = bbb;
			      ////////////////////////////////////////
			      
			      List<Integer> arr = new ArrayList<>();// 입력받을 리스트
			      List<Integer> list = new ArrayList<>();// 새로운 리스트 만들기

			      for (int i = mNum; i <= nNum; i++) { // 범위 값 배열에 넣어주기
			         arr.add(i);
			      }
			      int temp = 0;
			      int idx = 0;
			      // 1~99까지, 즉 10보다 크면 10의자리수 확인 후, 10의자리 동일하다면 1의 자리 판단
			      while (!arr.isEmpty()) { // 리스트 빌 때까지
			         int num = arr.get(0);
			         temp = num;
			         idx = 0;
			         for (int j = 0; j < arr.size(); j++) {
			            int num2 = arr.get(j);
			            if (temp >= 10 && num2 >= 10) { // num1이 10보다 크고, num2도 10보다 크다면
			               if (alpha[temp / 10] > alpha[num2 / 10]) { // 우선 순위가 크다면
			                  temp = num2; // 값 갱신
			                  idx = j;
			               } else if (temp / 10 == num2 / 10 && alpha[temp % 10] > alpha[num2 % 10]) {// 10의 자리가 동일하다면 1의자리를 비교
			                  temp = num2;
			                  idx = j;
			               }
			            } else if (temp < 10 && num2 >= 10) { // num1이 10보다 작고, num2가 10보다 크다면
			               continue;
			            } else {// 둘 다 10보다 작다면
			               if (alpha[temp] > alpha[num2]) {
			                  temp = num2;
			                  idx = j;
			               }
			            }
			         }
			         list.add(temp); // 우선순위가 제일 작은 애가 list에 추가
			         arr.remove(idx); // list 빠진 해당 인덱스 제거
			      }

			      int cnt = 0;
			      for (int i = 0; i < list.size(); i++) {
			         if (cnt!=0&&cnt % 10 == 0) {
			            sb.append("\n");
			         }
			         sb.append(list.get(i) + " ");
			         cnt++;
			      } 
			      

			      
			      
			      //////////////////////////////
				Integer[] nums = new Integer[nNum - mNum + 1];
				for (int i = 0; i <= nNum - mNum; i++) {
					nums[i] = mNum + i;
				}

				int[] order = { 10, 5, 9, 8, 3, 2, 7, 6, 1, 4 }; // 0~9까지의 가중치를 담은 배열

				// 정렬
				Arrays.sort(nums, new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						int ten1 = o1 / 10;
						int ten2 = o2 / 10;
						int one1 = o1 % 10;
						int one2 = o2 % 10;

						if (ten1 == ten2) { // 10의 자리가 동일하면 1의자리 기준으로 정렬
							return Integer.compare(order[one1], order[one2]);
						} else if (ten1 == 0) { // 둘 중 하나가 한자리 수면, 1의자리와 10의 자리 비교
							return Integer.compare(order[one1], order[ten2]);
						} else if (ten2 == 0) {
							return Integer.compare(order[ten1], order[one2]);
						} else { // 둘 다 두자리 수면 십의 자리 비교
							return Integer.compare(order[ten1], order[ten2]);
						}
					}
				});

				// 출력
				int cnt1 = 0;
				StringBuilder sb1 = new StringBuilder();
				for (int i = 0; i <= nNum - mNum; i++) {
					if (cnt1 != 0 && cnt1 % 10 == 0) {
						sb1.append("\n");
					}
					sb1.append(nums[i]).append(" ");
					cnt1++;
				}

				if(!sb1.toString().equals(sb.toString())) {
					System.out.print(mNum+" "+nNum+": ");
					System.out.println(sb1.toString().equals(sb.toString()));
					System.out.println(sb1.toString());
					System.out.println(sb.toString());
				}
			}
		}
	}
}