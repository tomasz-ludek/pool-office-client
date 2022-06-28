package pl.ludek.smat.home.pool_office_client.data.apiservice

import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData
import retrofit2.Call
import retrofit2.http.GET

interface ApiPoolInfoService {
    @GET("/pool-info")
    open fun getSensorData():Call<PoolInfoData>
}