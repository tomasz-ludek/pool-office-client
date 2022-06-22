package pl.ludek.smat.home.pool_office_client

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PoolInfoInterface {
    @GET("pool-info/")
    open fun getSensorData(): Call<PoolInfoData>

    companion object{
          var baseUrl = "http://127.0.0.1:8080/"
        fun create(): PoolInfoInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
            return retrofit.create(PoolInfoInterface::class.java)
        }
    }
}