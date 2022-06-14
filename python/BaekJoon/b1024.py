# 수열의 합
N, L = list(map(int,input().split()))
ans=[]
for l in range(L,101):
    if l%2==0:
        if (N/l-N//l)==0.5:
            mid=N//l
            if mid-l//2+1<0:
                continue
            for i in range(mid-l//2+1,mid+l//2+1):
                ans.append(i)
            break
    elif l%2==1:
        if N%l==0:
            mid=N//l
            if mid-l//2 < 0:
                continue
            for i in range(mid-l//2,mid+l//2+1):
                ans.append(i)
            break
    if N<l:
        break
if ans :
    print(" ".join(map(str,ans)))
else :
    print(-1)
