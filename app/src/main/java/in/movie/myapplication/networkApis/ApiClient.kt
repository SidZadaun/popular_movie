package `in`.movie.myapplication.networkApis


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private var retrofit: Retrofit? = null
    private var apiInterface: ApiInterface? = null


    private fun getClient(): Retrofit? {

        val requestToApiInterceptor = Interceptor { chain ->

            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", "d524fa94463c31188e6398f13d844207")
                .build()

            val request = chain.request()
                    .newBuilder()
                    .url(url.toString().replace("%26", "&"))
                .url(url.toString().replace("%2F", "/"))
                    .build()
            return@Interceptor chain.proceed(request)
        }

        val builder = OkHttpClient.Builder()
        builder.interceptors().add(requestToApiInterceptor)

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build())
                    .build()
        }
        return retrofit
    }

    fun startRequest(): ApiInterface? {
        apiInterface = getClient()?.create(ApiInterface::class.java)
        return apiInterface
    }

}

