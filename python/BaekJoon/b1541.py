# 잃어버린 괄호
sik = input().split('-')
for idx in range(len(sik)):
    temp = map(int,sik[idx].split('+'))
    total = 0
    for i in temp:
        total += i
    sik[idx] = total
answer = sik[0]
for idx in range(1,len(sik)):
    answer -= sik[idx]
print(answer)