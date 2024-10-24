# [Server] soundary-api-server

![Java](https://img.shields.io/badge/Java-17-red.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.9-green.svg)

# Branch
기본적으로 git-flow 전략을 따릅니다
```
 - `master`: 기준이 되는 브랜치로 제품을 배포하는 브랜치 입니다.
```

# Commit Rules
```
# feat: 새로운 기능 추가
# fix: 버그 수정
# docs: 문서 수정
# style: 코드 포맷팅 등 코드 내용 변경이 아닌 것
# refactor: 리팩토링
# test: 테스트 코드
# chore: 빌드, 패키지 매니저 등 설정 변경
# rename: 파일명 또는 폴더명을 수정한 경우
# remove: 코드 또는 파일의 삭제가 있는 경우

ex) feat: 사용자 서비스 추가
```

# 구동방법
```
api docker 실행
 - docker image 빌드(arm64): docker build --platform linux/arm64 -t "이미지이름" .
 - docker containers 실행: docker run -d -p 8080:8080 --name "컨테이너이름" "이미지이름":latest
 
api db 커넥션 설정
 - api로 docker를 빌드하는 경우 db 접속정보를 변경해야 함
 - lcaolhost -> host.docker.internal 
 
 ex) jdbc:mysql://host.docker.internal:8081/TEST
```

# 명령어
```
Redis 
 - docker redis-cli 접속: docker exec -it "컨테이너이름" redis-cli
 - 전체 키 조회: keys *
 - 값 조회: get "key"
 - ttl 조회: ttl "key"
```