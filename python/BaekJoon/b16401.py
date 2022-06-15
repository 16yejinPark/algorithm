# 과자 나눠주기

M, N = list(map(int,input().split()))
snacks = list(map(int,input().split()))

start = 1
end = max(snacks)
answer = 0
while start <= end:
    mid = (end+start)//2
    # 과자를 mid로 나눈 수들의 합이 M보다 크다면, mid보다 더 큰숫자도 가능하므로, start를 변경
    if sum([n//mid for n in snacks]) >= M:
        answer = mid
        start = mid + 1
    else:
        end = mid - 1
print(answer)
