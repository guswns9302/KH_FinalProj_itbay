# Itbay
> 중고 제품을 위탁 판매하는 중고 장터 사이트


![](../header.png)

## 개발 목표
- Spring Framework로 CRUD 게시판을 구현할 수 있다.  
- Mileage를 충전하고 사용하여 상품을 구매하도록 로직을 구현할 수 있다.  
- Kakao Login Service를 구현할 수 있다.  
- Websocket을 활용한 관리자와 이용자간의 Live Chatting 서비스를 구현할 수 있다.  


## 주요 기능
### 1. 소셜 로그인 (Kakao Login)  
img  
Kakao에서 제공하는 OAuth2 방식의 Login API를 이용
1. OAuth2 방식이란?  
Open Authorization의 약자로 사의 사이트에 대한 접근 권한을 얻고, 그 권한을 이용하여 개발할 수 있도록 도와주는 프로토콜이다.  
2. Kakao로부터 AccessToken을 발급받고, 그 토큰을 기반으로 Kakao Login Service에 접근한다.  

### 2. 마일리지 시스템  
결제 시스템을 구현하지 못하여 다른 서비스 로직을 생각하였다.  
1. 이용자가 원하는 만큼의 마일리지를 충전하도록 구현  
img  
2. 충전된 마일리지를 이용하여 제품을 구매하도록 구현  
img  
3. 충전 내역과 잔여 마일리지를 확인할 수 있는 기능 구현  
img

### 3. Live Chatting  
img  
WebSocket Protocol을 이용한 실시간 채팅을 구현

## 구현 기능
1. 회원 기능  
1.1 회원가입 및 탈퇴  
![가입탈퇴](https://user-images.githubusercontent.com/87797716/155833663-ad9a1d48-0198-4509-8922-40179d31e79a.png)
서비스 사용을 위한 회원 가입  
비밀번호 입력 시, 현재 로그인한 Session의 회원정보와 일치하면 ajax 비동기 통신으로 회원 탈퇴 처리를 한다.   
1.2 ID/PW 찾기  
![찾기](https://user-images.githubusercontent.com/87797716/155833589-b063a83e-831d-4116-9048-2990d4d32a30.png)
ajax 비동기 통신으로 이름과 전화번호를 입력받아 회원 ID / PW 를 찾는다.  
1.3 회원정보 조회  
![회원정보페이지](https://user-images.githubusercontent.com/87797716/155833699-68196360-de24-470e-8d0c-ed581d4b39e6.png)
회원 프로필과 로그인하며 저장된 Session을 통해 정보를 불러온다.  
프로필 수정을 통해 프로필 사진을 등록, 교체할 수 있다.    
회원정보 수정을 통해 ajax 비동기 통신으로 회원 전화번호, 주소를 변경할 수 있다.  
1.4 구매내역 조회  
![구매리스트페이지](https://user-images.githubusercontent.com/87797716/155833728-b4f3d7d7-9df4-46a7-9c57-8d4ae66d05ee.png)
로그인을 하며 저장된 Session 정보를 통해서 회원의 구매한 이력을 불러온다.  
1.5 장바구니  
![장바구니페이지](https://user-images.githubusercontent.com/87797716/155833734-4f1bdc63-5602-47ed-a103-18a8e874057e.png)
회원의 경우, 상품을 장바구니에 담아 관심상품을 몰아보고 구매할 수 있다.  
비회원의 경우, 상품을 장바구니에 담을 때 Cookie를 활용 한다.  
장바구니 페이지에 접근은 가능하지만, 구매하려면 회원가입 또는 로그인이 필요하다.   
2. 공지사항 게시판  
![공지사항게시판](https://user-images.githubusercontent.com/87797716/155833738-acf38db3-8caa-4341-830e-0df041f6ea55.png)
서비스를 사용에 필요한 공지를 등록하는 게시판이다.  
3. 중고 상품 게시판  
![판매게시판](https://user-images.githubusercontent.com/87797716/155833747-ccc73bed-3222-4e86-9c38-1f1687363399.png)
중고 제품을 구매하기 위한 상품 게시판이다.  
4. 구매 리뷰 게시판  
![구매리뷰페이지](https://user-images.githubusercontent.com/87797716/155833752-47e18e1a-67ba-4d40-b26c-45685bd6d8a1.png)
제품 구매 후, 리뷰 등록 게시판이다.  
4.1 댓글 기능  
img  
로그인한 회원들은 댓글기능을 사용할 수 있다.
5. 관리자 기능  
5.1 공지사항 게시  
img  
Spring Interceptor를 활용해 관리자 페이지를 구성  
관리자는 공지사항을 등록할 수 있다.  
5.2 중고상품 등록  
img  
JSTL의 c:if를 활용하여 관리자 로그인을 구분  
관리자는 제품을 등록할 수 있다.  
5.3 판매상품 조회  
img  
Header에서 JSTL의 c:if를 활용하여 관리자 로그인을 구분  
관리자는 제품 판매 내역을 조회하고 누적 판매금액을 확인할 수 있다.    
5.4 회원관리  
img  
Header에서 JSTL의 c:if를 활용하여 관리자 로그인을 구분  
회원 리스트를 불러와 회원의 통합적 관리가 가능하다.  

## Environment
Windows10, Tomcat, GitHub  
Spring MVC, Oracle DB, Mybatis, Maven, Websocket  
HTML5, CSS3, Javascript, Ajax
