# 물병
def main():
    N, K = list(map(int, input().split()))
    answer = 0;
    
    # bin은 문자열을 리턴하기 때문에 뒤에 count함수를 쓴거다.
    while(bin(N).count('1')>K):
        # ::-1을 통해 역순으로 배열하고 가장 먼저 발견한 '1'의 index를 반환
        plus = 2 ** (bin(N)[::-1].index('1'))
        answer += plus
        N += plus
    print(answer)
    
if __name__ == '__main__':
    main()