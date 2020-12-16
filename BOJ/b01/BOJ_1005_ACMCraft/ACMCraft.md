## ACM Craft

| 날짜   | 분류 | 번호 | 알고리즘 분류 |   |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| 20-12-15 | 백준 | 1005 | 위상 정렬 | [링크](https://www.acmicpc.net/problem/1005) |


<br/><br/>

### 문제 요약 

1. W번 건물을 건설 완료하는 데 걸리는 가장 짧은 시간 구하기
2. N 개의 건물을 짓는 순서가 주어짐 --> 위상 정렬 사용 
3. 선행 조건만 갖추어져 있다면 동시에 건설 가능
4. 모든 건물을 건설하는 것이 아니기 때문에 중간에 끝날 수 있음


<br/>

### 풀이

1. 선행 조건이 없는 (dgr[i]==0) 건물 번호를 queue에 넣음
2. while문 안에서 다음을 실행
   1. que에서 하나 꺼냄
   2. 현재 건물이 목표인 W번 건물이면 break
   3. 현재 건물이 선행조건인 (adj) 건물(target)들의 max 값을 갱신
      - 저장되어 있는 값 vs 지금 건물의 max + target의 건설시간
   4. target의 dgr 값 1 감소, 감소한 값이 0이면 que에 넣음


<br/>

### 비고

- list의 값을 조회할 때 그냥 for문보다 향상된 for문을 썼더니 속도가 빨라짐 왜지?
   - 검색해보니 성능의 차이는 별로 없다고 함 


<br/>

### sudo

```java
int T, N, K, buildtime[N+1], W
List<Integer> adj[N+1]
int dgr[N+1], max[N+1]
Queue<Integer> que

data read

for(1~N)
	if (dgr == 0)	 que.offer(i)

while(!que.isEmpty)
	int cur = que.poll
	if(cur == W)
		break;
	for (0~adj[cur].size-1)
		int target = adj[cur].get(i)
		max[target] = Math.max(max[target], max[cur]+buildtime[cur])
		if(--dgr[target] == 0)
			que.offer(target)

syso(max[W]+buildtime[W])
```




