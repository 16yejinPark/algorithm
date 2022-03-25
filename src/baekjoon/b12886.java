package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b12886 {

   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int nNum = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < nNum; i++) {
         String str = br.readLine();
         boolean flag = true;
         boolean check_flag = true;
         // 팰린드롬 검사
         int left = 0;
         int right = str.length() - 1;
         for(int j=0;j<str.length()/2;j++) {
            // 검사
            if (str.charAt(left) == str.charAt(right)) {
               left ++;
               right --;
            }else {               
                  if (str.charAt(left + 1) == str.charAt(right)&&left+1 !=right) {
                     left ++;
                     check_flag = false; // 유사회문
                     j--;
                  } else if(str.charAt(right - 1) == str.charAt(left)&&left !=right-1) {
                     right --;
                     check_flag = false; // 유사회문
                     j--;
                  }else {
                     flag= false; //유사회문이나 회문도 아닐때 
                     break;
                  }
            }
         }
         if (flag && check_flag) {
            sb.append("0\n");
         } else if (flag && !check_flag) {
            sb.append("1\n");
         } else {
            sb.append("2\n");
         }
      }
      System.out.println(sb);
   }
}