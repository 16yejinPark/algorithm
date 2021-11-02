package baekjoon;

import java.util.Scanner;

public class b15661 {
    static int N, ans;
    static int[][] graph;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        ans = Integer.MAX_VALUE;
        visit = new boolean[N];
        solve(0);
        System.out.println(ans);
    }

    private static void solve(int depth) {
        if (depth == N) {
            check();
            return;
        }
        visit[depth] = true;
        solve(depth + 1);
        visit[depth] = false;
        solve(depth + 1);
    }

    private static void check() {
        int start = 0;
        int link = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visit[i] != visit[j])
                    continue;
                if (visit[i])
                    start += graph[i][j] + graph[j][i];
                else
                    link += graph[i][j] + graph[j][i];
            }
        }
        int tmp = Math.abs(start - link);
        if (tmp < ans)
            ans = tmp;
    }
}