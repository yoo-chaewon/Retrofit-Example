# Sample

### # 날씨 API

- 사용 API

  https://openapi.sk.com

  SK open API - Weather Plan 사용

- API KEY 발급

- https://openapi.sk.com/resource/apidoc/indexView 👉 API Document 활용

  - 이번 프로젝트에서 사용할 API: 현재날씨(분별)
  - Request Parameters / Response Parameter 를 참고하여, 데이터를 가져오고, 필요한 것들만 파싱해서 사용.

- **Postman** 을 활용하여, 실제 API를요청하고, 응답을 확인해보기

- API를 요청하고 정상적으로 응답을 받을 수 있는지 확인



### # Gradle(dependency) 추가

```
// ✔️레트로핏 라이브러리
implementation 'com.squareup.retrofit2:retrofit:2.3.0' 

// ✔️네트워크 통신을 할때 json 받아오도록
implementation 'com.squareup.retrofit2:converter-gson:2.3.0' 

// 레트로핏과 rx 같이 쓸수있게 도와주는 라이브러리
implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0' 

// 네트워크할 때 로그를 보기위한 라이브러리
implementation 'com.squareup.okhttp3:logging-interceptor:3.9.1' 

// rxJava 등록
implementation 'io.reactivex.rxjava2:rxjava:2.1.0' 

// rxjava와 안드로이드와 같이 병행할수 있게 도와주는 라이브러리 (안드로이드 스케줄러)
implementation 'io.reactivex.rxjava2:rxandroid:2.0.1' 
```



### # Main View

- Activity_main.xml
  - 화면 구성 변경



### # 배경색/ 아이콘 삽입

- 아이콘 이미지 다운로드 : [https://www.flaticon.com](https://www.flaticon.com/)
- 앱 아이콘 생성 : [https://appiconmaker.co](https://appiconmaker.co/)
- 배경 그래디언트 : https://uigradients.com/#Atlas



### # 현재 위치 찍어보기

- Log.d로 현재 내 위치 찍어보기



### # NetworkCore

```kotlin
object NetworkCore {
    val api: Retrofit
    val BASE_URL = ""

    init {
        var okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    inline fun <reified T> getNetworkCore()  = api.create(T::class.java)
}
```

- baseURL 채우기



### # WeatherAPI

