package pl.ludek.smat.home.pool_office_client.data.apiservice

import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData
import pl.ludek.smat.home.pool_office_client.domain.model.RelayData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiPoolInfoService {
    @GET("/pool-info")
    open fun getSensorData():Call<PoolInfoData>

    @POST("/relay/0/1")
    open fun postRelayOneOn():Call<RelayData>

    @POST("/relay/0/0")
    open fun postRelayOneOff():Call<RelayData>

    @POST("/relay/5/1")
    open fun postRelayFiveOn():Call<RelayData>

    @POST("/relay/5/0")
    open fun postRelayFiveOff():Call<RelayData>

    @POST("/relay/256/1")
    open fun postRelayAllOn():Call<RelayData>

    @POST("/relay/256/0")
    open fun postRelayAllOff():Call<RelayData>
}