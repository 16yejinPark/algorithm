# 촌수계산

N = int(input())   #사람 수
X, Y = map(int,input().split())
M = int(input())   #관계 수

adj_list = [[0]*(N+1) for _ in range(N+1)]
for _ in range(M):
    x, y = map(int, input().split())
    adj_list[x][y] = 1
    adj_list[y][x] = 1

visited = [[False]*(N+1) for _ in range(N+1)]
queue = [[X,0]]
while queue:
    z = queue[0][0]
    n = queue[0][1]
    del queue[0]
    for i in range(1,N+1):
        if adj_list[z][i]==1 and visited[z][i]==False:
            if i==Y:
                print(n+1)
                exit()
            visited[z][i] = True
            queue.append([i,n+1])

print(-1)
exit()