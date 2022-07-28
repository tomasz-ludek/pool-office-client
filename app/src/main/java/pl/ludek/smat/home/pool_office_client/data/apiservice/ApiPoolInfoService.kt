package pl.ludek.smat.home.pool_office_client.data.apiservice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiPoolInfoService {
    @GET("/pool-info")
    fun getSensorData():Call<PoolInfoData>

    @POST("relay/{relayId}/{state}")
    fun switchRelay(
        @Path("relayId") relayId: Int,
        @Path("state") state: Int
    ): Call<RelayData>

    @GET("/relay")
    fun getInitializationState(): Call<InitializationStateRelay>
}