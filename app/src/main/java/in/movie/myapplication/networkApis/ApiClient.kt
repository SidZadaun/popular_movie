package `in`.movie.myapplication.networkApis


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private var retrofit: Retrofit? = null
    private var apiInterface: ApiInterface? = null


    private fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://resellme.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

    fun startRequest(): ApiInterface? {
        apiInterface = getClient()?.create(ApiInterface::class.java)
        return apiInterface
    }

}

