package pl.ludek.smat.home.pool_office_client.data.apiservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PoolInfoClientMod {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(DataCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiPoolInfoServiceMod = retrofit.create(ApiPoolInfoServiceMod::class.java)

     suspend fun getSensorData(): NetworkResult<PoolInfoData> {
        return service.getSensorData()
    }
}