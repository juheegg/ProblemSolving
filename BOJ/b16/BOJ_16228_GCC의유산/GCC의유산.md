## GCC의 유산

| 날짜     | 분류 | 번호  | 알고리즘 분류 |                                               |
| -------- | ---- | ----- | ------------- | --------------------------------------------- |
| 20-12-13 | 백준 | 16228 | 스택          | [링크](https://www.acmicpc.net/problem/16228) |

<br/>  

---

<br/>

### 문제 요약 

1. 괄호와 4종류의 이항 연산자가 포함된 식 계산하기
2. <?와 >?는 +와 -보다 우선 순위가 높음


<br/>

### 풀이

1. 후위 연산식으로 변경하기
   1. 숫자는 post에 add
   2. 연산자는 Stack 활용
      1. ( : 무조건 stack에  push
      2. ) : ( 가 나올 때까지 stack.pop()해서 post에 add
      3. +, -, <?, >? : stack의 top에 현재 우선순위보다 낮거나 같은 연산자가 있을 경우 pop해서 post에 add
2. 후위 연산식 계산하기
   1. 숫자는 무조건 stack에 넣음
   2. 연산자는 stack에서 숫자 두개를 꺼내서 계산하고 결과값을  stack에 다시 넣음 



<br/>

### 비고

- String Tokenizer 사용법!!!!
  - new StringTokenizer(String str, String delim, boolean returnDelims)
  - str: 쪼갤 String
  - delim: 구분 문자(쪼갤 단위들) --> "+-*/"를 넣으면 각각 문자로 쪼개줌 
  - returnDelims: 구분 문자를 버릴지(false) token으로 반환할지(true)

### sudo

```java
String line 
Stack<char> op
Stack<Integer> nums 
List<String> post

StringBuilder piece
for(0~line.length)
	char cur = line.charAt(i)
	if(isNum(cur))
		piece.append(cur)
	else
		우선순위에 따라 op stack에 push pop

for (0~post.size)
	nums stack 써서 계산		
```