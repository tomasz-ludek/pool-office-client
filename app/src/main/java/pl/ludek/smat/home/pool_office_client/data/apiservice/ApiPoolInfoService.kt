package pl.ludek.smat.home.pool_office_client.data.apiservice

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiPoolInfoService {
    @GET("/pool-info")
    suspend fun getSensorData(): NetworkResult<PoolInfoData>

    @POST("relay/{relayId}/{state}")
    suspend fun switchRelay(
        @Path("relayId") relayId: Int,
        @Path("state") state: Int
    ): NetworkResult<RelayData>

    @GET("/relay")
    suspend fun getInitializationState(): NetworkResult<InitializationStateRelay>
}