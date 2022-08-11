package pl.ludek.smat.home.pool_office_client.data.apiservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PoolInfoClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(DataCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiPoolInfoService = retrofit.create(ApiPoolInfoService::class.java)

    suspend fun switchRelay(relayID: Int, onOff: Boolean): NetworkResult<RelayData> {
        val state = if (onOff) 1 else 0
        return service.switchRelay(relayID, state)
    }

    suspend fun getSensorData(): NetworkResult<PoolInfoData> {
        return service.getSensorData()
    }

    suspend fun getInitializationStateRelay(): NetworkResult<InitializationStateRelay> {
        return service.getInitializationState()
    }
}