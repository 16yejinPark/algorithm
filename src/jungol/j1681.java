package JungOl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

 
//해밀턴 순환회로
public class j1681 {
    static int min=Integer.MAX_VALUE;
    static int N;
    static int[][] adjarr;
    static boolean visited[];
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        adjarr = new int[N][N];
        visited = new boolean[N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                adjarr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited[0]=true;
        dfs(0,1,0);
        System.out.println(min);
    }
     
    public static void dfs(int n,int cnt,int total) {
        if(total>min) {
            return;
        }
        if(cnt==N) {
            if(adjarr[n][0]==0)return;
            total+=adjarr[n][0];
            min=Math.min(total, min);
        }else {
            for(int i=0;i<N;i++) {
                if(adjarr[n][i]!=0&&!visited[i]) {
                    visited[i]=true;
                    dfs(i,cnt+1,total+adjarr[n][i]);
                    visited[i]=false;
                }
            }
        }
    }
     
}
