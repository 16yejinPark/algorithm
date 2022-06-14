# 미로 탐색
dx = [-1,1,0,0]
dy = [0,0,-1,1]

if __name__ == '__main__':
    N, M = list(map(int, input().split()))
    maze = []
    # _는 i dont care
    for _ in range(N):
        # map은 예약어이기 때문에 변수로 쓸 수 없다.
        # map.append(list(map(int, input())))
        
        # list는 무조건 한글자씩 list로 만들어주는 듯 하다.
        maze.append(list(map(int, input())))
    
    queue = [[0,0]]
    # 0으로 초기화 되어있는 배열 만들기
    visited = [[0]*M for _ in range(N)]
    visited[0][0] = 1
    # queue가 비어있지 않다면 알아서 true리턴
    while queue:
        x, y = queue[0][0],queue[0][1]
        del queue[0]
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0<=nx<N and 0<=ny<M and visited[nx][ny]==0 and maze[nx][ny]==1:
                if(nx==N-1 and ny==M-1):
                    print(visited[x][y]+1)
                    exit()
                visited[nx][ny]=visited[x][y]+1
                queue.append([nx,ny])
