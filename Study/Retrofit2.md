# Retrofit 2

`FrontEnd` 개발을 하다보면 필수적으로, 서버에서 데이터를 요청할 일이 생김.

이를 편하게 하기 위한 라이브러리.



### Retrofit 이란?

- `TypeSafe` 한 HttpClient라이브러리임.

  👉 네트워크로 부터 전달된 데이터를 우리 프로그램에서 필요한 형태의 객체로 받을 수 있다는 의미.

- Http기반 **REST API**(GET, POST, PUT, DELETE)를 Retrofit2라이브러리를 통해 사용.

  👉 서버와 클라이언트간 Http 통신을 위한 인터페이스

- REST API의 표현식인 JSON을 Object개념으로 바꿔주는 GSON라이브러리를 함께 사용.

- Retrofit은 기본적으로 OKHttp에 의존하고 있음.

즉, 클라이언트에서 서버로 어떠한 요청을 보내면 서버는 그 요청에 대한 응답을 클라이언트로 보내주게 되는데, 이러한 과정들을 쉽게 사용할 수 있도록 도와주는 역할을 하는 것이 Retrofit



### 왜? Retrofit? 

- Http Rest API통신을 위한 라이브러리

- @anotation형식을 통해 사용

- 인터페이스를 통해 쉽게 구현

- JSON을 간단하게 처리할 수 있음

  > JSON: Key - value의 조합의 데이터
  >
  > ```json
  > {
  >     "weather": {
  >         "minutely": [
  >             {
  >                 "wind": {
  >                     "wdir": "346.90",
  >                     "wspd": "9.60"
  >                 },
  >                 "precipitation": {
  >                     "sinceOntime": "0.00",
  >                     "type": "0"
  >                 },
  >                 "sky": {
  >                     "code": "SKY_A07",
  >                     "name": "흐림"
  >                 }
  >             }
  >         ]
  >     }
  > }
  > ```
  >



### +) GSON라이브러리

- JSON을 객체로 사용할 수 있게 변환할 때 사용하는 라이브러리.
- JSON의 Key와 객체의 변수명과 타입을 맞춰야 함.



### 그 외의 안드로이드 통신방법

- HttpURLConnection / Volley / OkHttp 등이 있다.
- HttpURLConnection: android기본제공. 구현할 것이 많음.
- Volley: 구글에서 제공하는 라이브러리. 현재는 대부분 Retrofit으로 넘어가면서 많이 사용하지 않는다.
- Retrofit: Square사에서 제공하는 오픈소스 라이브러리. 빠른 속도와 제공하는 것이 많아 대부분 앱 개발과정에서 많이 사용된. OKhttp도 비슷.



### HTTP란 ?

URL접속을 통해 데이터를 읽어오는 방법. 즉 www상에서 정보를 주고받을 수 있는 프로토콜이다. HTTP는 서버와 클라이언트 사이에서 request/response을 웹브라우저(url)을 통해 통신한다.



### 통신의 주요 메소드

| 메소드 | 역할            |
| ------ | --------------- |
| GET    | 데이터 받기     |
| POST   | 데이터 생성     |
| PUT    | 데이터 업데이트 |
| DELETE | 데이터 삭제     |

- GET: URL혹은 URI에 변수(쿼리)를 포함 시켜(안할 수도 있음!) 데이터를 받는 방식

- POST: 데이터를 Body(일종의 객체)에 감싸서 보내는 방식

- PUT: POST와 마찬가지로 Body에 갑싸서 보내는 방식, 성공적으로 보내면 데이터가 업데이트 된다.

- DELETE: 성공적으로 보내면 데이터가 삭제 된다.

  

## 참고

- http 통신 / socket 통신
- retrofit [https://github.com/square/retrofit]
- Android 통신 라이브러리의 역사를 되돌아본다 [https://pluu.github.io/blog/android/2016/12/25/android-network/]
- https://academy.realm.io/kr/posts/droidcon-jake-wharton-simple-http-retrofit-2/