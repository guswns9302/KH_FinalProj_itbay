# Itbay
> 중고 제품을 위탁 판매하는 중고 장터 사이트

<img src="https://user-images.githubusercontent.com/87797716/155836186-8f6cda8d-7622-49bb-a073-e51cfe834e6f.png" width="650px" height="350px"></img>  

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
2. Kakao로부터 AccessToken을 발급받는다.  
<img src="https://user-images.githubusercontent.com/87797716/155881195-2d5dc74f-f831-477a-a4f5-918a0144e5aa.png"></img>  
3. AccessToken을 기반으로 Kakao Login Service에 Login요청을 하여 Kakao에 동의한 회원 정보를 불러온다.  
<img src="https://user-images.githubusercontent.com/87797716/155881283-97bfcade-f24c-4e58-b65d-05b1370ae953.png"></img>  

### 2. Live Chatting  
WebSocket Protocol을 이용한 사용자와 관리자 사이의 1:1 실시간 채팅 구현  
<img src="https://user-images.githubusercontent.com/87797716/156277041-8680c1e9-07de-480d-bd94-0148335b6cf3.png"></img>  
* 사용자 : 메세지 입력  
<img src="https://user-images.githubusercontent.com/87797716/156277485-e0358798-e102-44d6-8faa-e0c8ec231e65.png" width="450px"></img>
<img src="https://user-images.githubusercontent.com/87797716/156277445-1b780a45-4b96-4137-9f81-5596d02f8e0b.png" width="450px"></img>  
* 관리자 : 채팅방 생성 확인 / 메세지 입력  
1. 이용자별로 Private한 채팅시스템을 위해 채팅방 개념 구현    
1.1 어느 페이지에서든 채팅시스템을 이용할 수 있도록 상단 네비게이션 bar를 header로 사용   
<img src="https://user-images.githubusercontent.com/87797716/156278309-06ea97dd-b4a7-4371-a573-8cbf23c06b45.png"></img>  
1.2 채팅방을 구현하기위해, 사용자와 관리자 페이지 분리   
<img src="https://user-images.githubusercontent.com/87797716/156278524-798806eb-050b-4679-b87b-cec510cf2812.png" width="450px"></img>  
<img src="https://user-images.githubusercontent.com/87797716/156278596-35e58c47-aebc-432a-8240-0a14e333a31c.png" width="450px"></img>  
1.3 사용자 별로 socket 연결 url을 달리하여 채팅방 개념 적용  
<img src="https://user-images.githubusercontent.com/87797716/156280803-e04b32ef-b166-4a30-b429-fd472604d5f1.png"></img>  
Bean 설정 : socket 연결 url을 localhost:80/chat/(회원번호)로 연결하기위해 paht=/chat/* 적용  
<img src="https://user-images.githubusercontent.com/87797716/156280722-1139e96f-7e93-4b41-9c3b-8fb96e687d51.png"></img>  
socket 연결 : socket 연결 url을 localhost:80/chat/(회원번호)로 연결하기위해 session에 저장된 data 사용  
1.4 관리자 -> ajax로 생성된 채팅방 List를 가져옴  
<img src="https://user-images.githubusercontent.com/87797716/156281830-40fbe37c-f97f-47c2-ad0e-6470b3aaf086.png"></img>  
controller : list에 저장된 채팅방 data를 JSON data로 저장  
ex) {[채팅방 번호, 회원 아이디], [1,회원1], [2, 회원2]}   
<img src="https://user-images.githubusercontent.com/87797716/156281534-ef5c27f1-d8c5-462b-8594-a8015874bde1.png"></img>  
jsp : 채팅방 list.size만큼 button을 생성하며 생성된 버튼을 클릭하면 해당 채팅방 번호를 받아와 socket 연결  
2. 페이지 이동 시, socket연결이 재연결되며 대화 이력을 불러 올 수 있도록 구현  
2.1 메세지 입력을 할 때마다 DB에 저장  
ajax를 통해 Controller 연결  
<img src="https://user-images.githubusercontent.com/87797716/156282355-2b130128-1c69-4f37-8215-a72dc6c91ac6.png"></img>  
Controller는 전송할 메세지와, 채팅방 번호를 전달 받아 db에 저장
<img src="https://user-images.githubusercontent.com/87797716/156282514-169c9be9-7227-4413-aba1-53f2d22d19f6.png"></img>  
2.2 socket 연결 시, db에서 해당 방번호로 채팅 이력을 가져옴  
ajax를 통해 Controller 연결  
<img src="https://user-images.githubusercontent.com/87797716/156283152-d299dfe4-5d09-40d1-b1da-ee4e081e368f.png"></img>  
Controller는 채팅방 번호를 전달 받아 list에 대화 이력을 불러와 data를 JSON data로 정
<img src="https://user-images.githubusercontent.com/87797716/156282514-169c9be9-7227-4413-aba1-53f2d22d19f6.png"></img>  

### 3. 마일리지 시스템  
결제 시스템을 구현하지 못하여 다른 서비스 로직을 생각하였다.  
1. 이용자가 원하는 만큼의 마일리지를 충전하도록 구현  
<img src="https://user-images.githubusercontent.com/87797716/155881667-db1b4845-928b-4568-a1cb-699428bcd631.png"></img>  
2. 충전된 마일리지를 이용하여 제품을 구매하도록 구현  
<img src="https://user-images.githubusercontent.com/87797716/155881691-a8eb37e2-0904-48d2-84d2-ea7ed7e80721.png"></img>  
* 보유 마일리지보다 가격이 낮으면 구매 완료 후 구매내역으로 페이지 이동  
* 보유 마일리지보다 가격이 높으면 마일리지 충전 페이지로 이동  
3. 충전 내역과 잔여 마일리지를 확인할 수 있는 기능 구현  
<img src="https://user-images.githubusercontent.com/87797716/155881647-bdba042a-b439-4890-8441-55785e277bc2.png"></img>  

## 구현 기능
1. 회원 기능  
1.1 회원가입 및 탈퇴  
<img src="https://user-images.githubusercontent.com/87797716/155833663-ad9a1d48-0198-4509-8922-40179d31e79a.png"></img>  
서비스 사용을 위한 회원 가입  
비밀번호 입력 시, 현재 로그인한 Session의 회원정보와 일치하면 ajax 비동기 통신으로 회원 탈퇴 처리를 한다.   
1.2 ID/PW 찾기  
<img src="https://user-images.githubusercontent.com/87797716/155833589-b063a83e-831d-4116-9048-2990d4d32a30.png"></img>
ajax 비동기 통신으로 이름과 전화번호를 입력받아 회원 ID / PW 를 찾는다.  
1.3 회원정보 조회  
<img src="https://user-images.githubusercontent.com/87797716/155833699-68196360-de24-470e-8d0c-ed581d4b39e6.png" ></img>  
회원 프로필과 로그인하며 저장된 Session을 통해 정보를 불러온다.  
프로필 수정을 통해 프로필 사진을 등록, 교체할 수 있다.    
회원정보 수정을 통해 ajax 비동기 통신으로 회원 전화번호, 주소를 변경할 수 있다.  
1.4 구매내역 조회  
<img src="https://user-images.githubusercontent.com/87797716/155833728-b4f3d7d7-9df4-46a7-9c57-8d4ae66d05ee.png" ></img>  
로그인을 하며 저장된 Session 정보를 통해서 회원의 구매한 이력을 불러온다.  
1.5 장바구니  
<img src="https://user-images.githubusercontent.com/87797716/155833734-4f1bdc63-5602-47ed-a103-18a8e874057e.png" ></img>  
회원의 경우, 상품을 장바구니에 담아 관심상품을 몰아보고 구매할 수 있다.  
비회원의 경우, 상품을 장바구니에 담을 때 Cookie를 활용 한다.  
장바구니 페이지에 접근은 가능하지만, 구매하려면 회원가입 또는 로그인이 필요하다.   
2. 공지사항 게시판  
<img src="https://user-images.githubusercontent.com/87797716/155833738-acf38db3-8caa-4341-830e-0df041f6ea55.png" ></img>  
서비스를 사용에 필요한 공지를 등록하는 게시판이다.  
3. 중고 상품 게시판  
<img src="https://user-images.githubusercontent.com/87797716/155833747-ccc73bed-3222-4e86-9c38-1f1687363399.png" ></img>  
중고 제품을 구매하기 위한 상품 게시판이다.  
4. 구매 리뷰 게시판  
<img src="https://user-images.githubusercontent.com/87797716/155833752-47e18e1a-67ba-4d40-b26c-45685bd6d8a1.png" ></img>  
제품 구매 후, 리뷰 등록 게시판이다.  
4.1 댓글 기능  
<img src="https://user-images.githubusercontent.com/87797716/155879996-8e31b9b5-f447-4f4a-a4b6-bd71ebbadf61.png" ></img>  
로그인한 회원들은 댓글기능을 사용할 수 있다.
5. 관리자 기능
5.1 공지사항 게시  
<img src="https://user-images.githubusercontent.com/87797716/155880066-2cb3c09a-83b7-45be-9de6-b7787ac78559.png" ></img>  
Spirng Interceptor을 통해서 관리자 페이지 구성  
관리자는 공지사항을 등록할 수 있다.  
5.2 중고상품 등록  
<img src="https://user-images.githubusercontent.com/87797716/155880087-bec7522f-2581-4205-95d7-0db6c9e34220.png" ></img>  
JSTL의 c:if를 활용하여 관리자 로그인을 구분  
관리자는 제품과 제품 사진을 등록할 수 있다.  
5.3 판매상품 조회  
<img src="https://user-images.githubusercontent.com/87797716/155880775-8c36400b-025a-4160-87f4-b3903c6955ed.png" ></img>    
Header에서 JSTL의 c:if를 활용하여 관리자 로그인을 구분  
관리자는 제품 판매 내역을 조회하고 누적 판매금액을 확인할 수 있다.    

## Environment
Windows10, Tomcat, GitHub  
Spring MVC, Oracle DB, Mybatis, Maven, Websocket  
HTML5, CSS3, Javascript, Ajax
