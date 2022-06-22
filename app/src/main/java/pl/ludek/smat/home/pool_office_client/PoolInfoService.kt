package pl.ludek.smat.home.pool_office_client

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PoolInfoService {
    @GET("pool-info")
    open fun getSensorData():Call<MutableList<PoolInfoData>>

}