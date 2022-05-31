# SSAFY - 🏆우수 프로젝트 HappyHouse

<br>
<br>

![개발환경](https://user-images.githubusercontent.com/58455540/171114046-5aae1bff-00b1-4c8f-84a7-c81c1032949e.png)

- 형상관리툴로는 git을 사용했습니다.
- Mattermost와 Webex를 활용하여 팀원과 의사소통했습니다.
- SpringBoot를 활용하여 backend Server를 구성했습니다.
- SQL Mapper로 Mybatis를 사용했습니다.
- SPA로는 Vue를 선택했습니다.
<hr>

![전체시스템구조도](https://user-images.githubusercontent.com/58455540/171114698-819486fd-2dea-4e27-aa9b-0098eeca982a.png)

- 전체 시스템 구조도는 위와 같습니다.
- 맵을 가져오는 부분은 Vue2-google-map을 사용했습니다.
- jsoup을 이용하여 네이버 부동산을 크롤링하여 뉴스를 보여주도록 했습니다.
- 전체적인 디자인은 BootstrapVue를 활용했습니다.

<hr>
<br>

## ⭐ 메인화면

![메인화면](https://user-images.githubusercontent.com/58455540/171137119-55243413-1815-4aec-ace1-306d3fa2ab67.png)

- 메인화면은 좌측에 네비게이션바를 두었고, 어떠한 서비스를 제공할 수 있는지를 두었습니다.

<hr>
<br>

## 🙍‍♀️ 회원관리

![회원관리](https://user-images.githubusercontent.com/58455540/171176244-bfed5dab-6db1-45b8-8faf-caa3206f8915.png)

- jwt를 활용하여 access token을 서버에서 생성하고 회원의 세션스토리지에 저장하는 형식으로 구현했습니다.

<hr>
<br>

## 🏢 아파트매물검색

![아파트매물검색](https://user-images.githubusercontent.com/58455540/171177036-99aa7a0e-d182-4ebf-98d5-86088fc1b8f6.png)

- 공공데이터포털을 통해 아파트 매물을 받아와서 MySQL DB에 저장하여 구현했습니다.
- 지역기반으로 검색하면 해당 지역의 아파트 매물을 가져오도록 SQL문을 작성하였습니다.

<hr>
<br>

## 🏪 상권보기

![상권보기](https://user-images.githubusercontent.com/58455540/171177746-d47c3a12-c854-4eeb-bd9a-bd1f4bdba5fe.png)

- 공공데이터포털을 통해 전국 상권 정보를 DB에 저장하여 구현했습니다.

<hr>
<br>

## 😍 관심매물등록

![관심매물등록](https://user-images.githubusercontent.com/58455540/171178333-a93fb7e9-10ce-403f-8041-91d22596ee82.png)

- Maker의 ❤를 클릭하면 관심매물이 등록되고 관심매물탭에서 확인할 수 있도록 했습니다.

<hr>
<br>

## 📰 News

![News](https://user-images.githubusercontent.com/58455540/171179000-ff8ad332-12d0-4101-8378-8b9b73f7e0d6.png)

- jsoup를 활용하여 네이버 부동산 뉴스를 크롤링하여 보여주도록 했습니다.

<hr>
<br>

## ✨ 인기매물순위

![인기매물순위](https://user-images.githubusercontent.com/58455540/171179550-5d84e3cb-cf1c-41fb-9572-f48791deb500.png)

- 관심매물로 등록한 유저들의 숫자를 기준으로 인기매물 중 관심도가 가장 많은 5개의 매물을 보여주도록 했습니다.

<hr>
<br>

## 📈 지역별 평당 거래가

![지역별평당거래가](https://user-images.githubusercontent.com/58455540/171179741-f36c4d83-bd12-41ea-a17d-7a7a10727034.png)

- 지역별 평당거래가를 SQL문으로 작성하여 그래프로 표현했습니다.

<hr>
<br>

## 👫 TEAM

![팀](https://user-images.githubusercontent.com/58455540/171179879-f24f665d-45f8-47ac-b6ab-366ef3059019.png)

- 김이준 - News 크롤링, 이미지 및 로고 디자인
- 박명규 - 그 외 프론트, 백엔드 개발 (아파트매물, 상권, 인기매물, 관심매물, 회원관리, 평당거래가)
