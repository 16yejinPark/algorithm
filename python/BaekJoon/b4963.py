# 섬의 개수
import sys

dx = [-1,-1,-1,0,1,1,1,0]
dy = [-1,0,1,1,1,0,-1,-1]

while True:
    W, H = list(map(int,input().split()))
    if W == 0 and H == 0:
        break
    ymap= []
    for _ in range(H):
        ymap.append(sys.stdin.readline().rstrip().split())
    cnt = 0
    visited = [[False] * W for _ in range(H)]
    for i in range(H):
        for j in range(W):
            if ymap[i][j]=='1' and visited[i][j]==False:
                que = [[i,j]]
                visited[i][j] = True
                cnt += 1
                while que:
                    x = que[0][0]
                    y = que[0][1]
                    del que[0]
                    for d in range(8):
                        nx = x + dx[d]
                        ny = y + dy[d]
                        if 0<=nx<H and 0<=ny<W and visited[nx][ny]==False and ymap[nx][ny]=='1':
                            visited[nx][ny] = True
                            que.append([nx,ny])
    print(cnt)
