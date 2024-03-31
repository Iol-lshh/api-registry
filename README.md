![클래스 관계 초안](./docs/erd/클래스_관계_초안.png)

# core
## 도메인 분류
### router
### resource
#### resourcer
- 자원 제공자
#### argument
- 자원 인자
#### query
- 자원 질의 내용
### task
- 작업
#### lock
- 잠금 작업(키락)
#### pipeline
- 순차적 파이프라인 작업
#### parallel
- 병렬(동시 경합성) 작업
## 명칭 분류
### Manager
- 캐시 관리
- 데이터 관리
### Provider
- 데이터 제공
### Repository
- 데이터 저장소
### Domain
- 기능
### Context
- 영속성 데이터
- entity
### dto
#### Command
- 명령
##### CreateCommand
- 신규 객체 생성 명령
##### UpdateCommand
- 객체 수정 명령
#### View
- 전달 객체

# config
- core를 bean으로 등록(예제)

# infrastructure
- core를 인프라로 구현한 구현체
