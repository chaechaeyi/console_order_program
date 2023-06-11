# homework - 상품 주문 프로그램

## 프로젝트 실행
HomeworkApplication.java 실행 -> h2 db에 테이블 생성 및 데이터 적제(data.sql 내부 쿼리로 인하여 데이터 삽입)
-> OrderApplication가 실행 되며 console을 통해서 주문 프로그램을 사용할 수 있습니다. 

## 일정 관리 
https://github.com/users/chaechaeyi/projects/3/views/4

## Environments
* Amazon Corretto Version 17.0.7
* Spring Boot 3.1.0
* Gradle 
* H2
* JPA 
* lombok 

## 데이터 연관관계 (ERD)
document 패키지 아래 : 주문프로그램.데이터연계도.svg 참고 부탁드립니다.

## Process
### 이용권 만료
```mermaid
sequenceDiagram
    actor User
    participant Item
    participant Order
    participant OrderItem
    User->>Item: operation - o, order(주문시작:상품리스트조회)
    activate Item
    Item->>User: item list
    
    User->>Item: input 상품 id
    Item->>User: 상품 존재여부 확인
    Note over User: input 상품 수량 입력
    User->>Order: space bar+enter(주문실행)
    
    Order->>OrderItem: 주문 상품 정보 저장
    OrderItem->>Item: 주문 상품 재고 차감
    Note over Item, OrderItem: 재고 부족 시 SoldOutException 발생(오류 발생 시 처음부터 다시 시작)  
    
    Item->>User: 주문 상품 및 결재 정보 제공    
```
