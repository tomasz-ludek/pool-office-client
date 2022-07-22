package pl.ludek.smat.home.pool_office_client.data.apiservice

import pl.ludek.smat.home.pool_office_client.domain.model.InitializationStateRelay
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData
import pl.ludek.smat.home.pool_office_client.domain.model.RelayData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PoolInfoClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiPoolInfoService = retrofit.create(ApiPoolInfoService::class.java)

    fun switchRelay(relayID:Int, onOff: Boolean): Call<RelayData> {
        val state = if(onOff) 1 else 0
        return service.switchRelay(relayID, state)
    }

    fun getSensorData(): Call<PoolInfoData> {
        return service.getSensorData()
    }

    fun getInitializationStateRelay():Call<InitializationStateRelay>{
        return service.getInitializationState()
    }
}