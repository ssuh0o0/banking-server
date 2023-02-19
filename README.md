# Banking Server
동시성 문제를 해결한 뱅킹 서버 구현 프로젝트

<br/>

## Overview
---
대용량 트래픽에도 견딜 수 있게 시스템을 설계해보고, 동시성 문제를 해결한 프로젝트를 해보고 싶어 시작하게 되었습니다.

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

## Decription
---
