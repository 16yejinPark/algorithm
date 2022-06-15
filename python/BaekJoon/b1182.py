# 부분수열의 합
def subset(idx,sum):
    global answer
    #print(idx)
    if idx>=N:
        return
    sum += ls[idx]
    if sum == S:
        answer += 1
    subset(idx + 1, sum)
    subset(idx + 1, sum - ls[idx])

N, S = map(int,input().split())
ls=list(map(int,input().split()))
answer = 0
subset(0,0)
print(answer)

