package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//무기 공학
public class b18430 {
   static int N;
   static int M;
   static int maxTotal=0;
   static int[] dx = {-1,0,-1,0,0,1,1,0};
   static int[] dy = {0,-1,0,1,-1,0,0,1};
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      int[][] wood = new int[N][M];
      for(int n=0;n<N;n++) {
         st = new StringTokenizer(br.readLine());
         for(int m=0;m<M;m++) {
            wood[n][m] = Integer.parseInt(st.nextToken());
         }
      }
      dfs(wood,0,0,0);
      System.out.println(maxTotal);
   }
   static void dfs(int[][] wood,int total,int r,int c) {
      System.out.println("total="+total);
      for(int n=0;n<N;n++) {
         for(int m=0;m<M;m++) {
            System.out.printf("%d ",wood[n][m]);
         }
         System.out.println();
      }
      System.out.println();   
      maxTotal = Math.max(maxTotal, total);
      for(int n=0;n<N;n++) {
         for(int m=c;m<M;m++) {
            if(wood[n][m]!=-1) {
               for(int d=0;d<8;d+=2) {
                  int nn1 = n+dx[d];
                  int nm1 = m+dy[d];
                  if(nn1<0||nn1>=N||nm1<0||nm1>=N||wood[nn1][nm1]==-1) {
                     continue;
                  }
                  int nn2 = n+dx[d+1];
                  int nm2= m+dy[d+1];
                  if(nn2<0||nn2>=N||nm2<0||nm2>=N||wood[nn2][nm2]==-1) {
                     continue;
                  }
                  int temp1=wood[nn1][nm1];
                  int temp2=wood[nn2][nm2];
                  int temp3=wood[n][m];
                  
                  wood[nn1][nm1]=-1;
                  wood[nn2][nm2]=-1;
                  wood[n][m]=-1;
                  dfs(wood,total+(temp1+temp2+(temp3*2)),r,c+1);
                  wood[nn1][nm1]=temp1;
                  wood[nn2][nm2]=temp2;
                  wood[n][m]=temp3;
               }
            }
         }
      }
   }
}