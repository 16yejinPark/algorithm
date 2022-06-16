# 베르트랑 공준
import sys
from math import sqrt

prime = [True]*250001
prime[1] = False
for i in range(2,250001):
    if prime:
        for j in range(i,250001,i):
            if i==j:
                continue
            else:
                prime[j]=False
while True:
    N = int(sys.stdin.readline().rstrip())
    if N == 0:
        break
    else:
        cnt = 0
        print(sum(prime[N+1:N*2+1]))


