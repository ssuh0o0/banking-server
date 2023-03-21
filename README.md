# Banking Server
동시성 문제를 해결한 뱅킹 서버 구현 프로젝트

<br/>

## Overview
---
1. 객체지향 원리를 이해 
2. 클린 코드 규칙을 준수하여 올바른 코드 작성
3. 계좌이체 시 발생 가능한 동시성 제어 + 트랜잭션 원자성을 보장
4. 추가 기능을 구현하여, 코드 유지보수성 테스트

추가) 대용량 트래픽까지 고려한 뱅킹 서버를 구축

<br/>

## Feature
---
* 친구 추가 API
* 내 친구 목록 조회 API
* 계좌 이체 API
    - 계좌 이체는 친구끼리만 가능
    - 계좌 이체는 트랜잭션 원자성이 보장
    - 하나의 계좌에 동시에 돈이 입금 가능
    - 계좌 이체가 완료된 이후에는 푸시 알람
* 계좌 조회 API (내 계좌만 조회 가능)
* 회원가입 API

<br/>

## Project Environment
---
* Framework & Language
    * Spring Boot 3.0.2
    * Java 17

* Library
    * Spring-Data-JPA
    * Spring-Security
    * Spring-Web
    * Lombok
    * Jacoco 0.8.8
    * MySQL Driver

* Database
    * MySQL 8.0

* VCS
    * GitHub

* Communication Tool
    * Dicord Webhook
    * notion

<br/>

## ERD
---
![image](https://user-images.githubusercontent.com/55631147/222216030-daaff612-1128-4f83-a5d4-e8cab7c20cde.png)
