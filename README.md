# SPGP 2차 발표

## 게임 제목
- **포켓몬 디펜스**

## 게임 컨셉
![게임 컨셉](https://user-images.githubusercontent.com/70653452/160658340-f106ce23-7b9a-4800-9542-7e951733ad66.PNG)
- 포켓몬과 디펜스 게임을 합친 모바일 게임

## 개발 범위
![image](https://user-images.githubusercontent.com/70653452/160665220-6553afd7-b379-4bee-a6d6-314c79a5cde6.png)

## 진행 상황
![image](https://user-images.githubusercontent.com/70653452/166465039-e9442bdc-ea71-4061-979f-cb61271f0755.png)
- 공격 이펙트 리소스 부족
- 적 AI의 HP 미설정, Generator 미구현

## 커밋
![image](https://user-images.githubusercontent.com/70653452/166462238-73c98773-a3a1-460f-8153-bbe35b187ce4.png)


## 게임 오브젝트

### 적AI
![image](https://user-images.githubusercontent.com/70653452/166462697-af7804ad-c449-4628-aea7-9c9addf50a74.png)
- 버튼 클릭시 웨이브 시작
- AnimStart() 함수를 통해 적이 이동경로를 따라 이동
- 이동경로는 anims.xml으로 구현

### 상점
![image](https://user-images.githubusercontent.com/70653452/166463279-61f23918-6a07-4cf3-87fa-e4a1a1bad0a3.png)
- 상점 버튼 클릭시 포켓몬 목록, 재화 상태, 리롤 버튼이 뜬다
- 리롤 버튼을 누르면 현재 상점에 있는 포켓몬들이 다시 랜덤한 포켓몬으로 교체된다
- 리롤 버튼을 누르면 -2원 차감된다
- 포켓몬을 누르면 -1원 차감된다 (추가 구현 예정)

## 수업에서 추가로 다루었으면 하는 사항
- 곧 타일마다 포켓몬을 배치하는 작업을 해야하는데, 이 타일들을 어떤식으로 영역을 나누어야 할지 고민입니다.

