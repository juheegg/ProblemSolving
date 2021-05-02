## Restore Ip Addresses

| 날짜   | 분류 | 번호 | 알고리즘 분류 |                                          |
| ------ | ---- | ---- | ------------- | ---------------------------------------- |
| 21-03-24 |  Leet Code   |   93   |   재귀, backtracking     | [링크](https://leetcode.com/problems/restore-ip-addresses/) |


<br/><br/>

### 문제 요약 

1. 숫자로만 이루어진 문자열이 주어졌을 때 이 문자열에서 가능한 모든 IP 주소를 담은 List 반환
   - 순서는 상관 X
2. 유효한 IP주소의 조건
   - 4개의 정수로 이루어짐
   - 정수 사이 '.'으로 구분
   - 0~255만 가능 
   - 0을 제외하고 맨 앞에 0이 있으면 안됨
      - ex) 0.0.32.34 => 가능
      - ex) 255.255.055.0 => 불가능


<br/>

### 풀이

1. 재귀로 풀었음
2. depth==4가 되면 가능한 ip이므로 list에 추가
3. 주어진 문자열을 총 4개로(이게 depth) 쪼개야 하는데 각 depth마다 맨 앞에서부터 문자열 1개/2개/3개씩 검사
   - canIP로 유효한 IP주소를 구성하는 숫자인지 확인
   - 가능하다면 재귀함수 호출
4. 현재 남은 depth의 개수가 str의 개수보다 작으면 더이상 조각을 못만드니까 바로 return
   - 현재 남은 depth의 개수*3보다 str의 길이가 크면 최대로 조각 4개를 만들어도 숫자가 남으니까 바로 return


<br/>

### 비고

- 난생 처음 leet code 문제를 풀어보았다 스터디원 ㅈㅅ님 감사,,,
- 문제가 영어라 처음에 쫄았는데 생각보다 어렵지 않은 영어였음 ㅋ 


<br/>

### sudo

```java

```