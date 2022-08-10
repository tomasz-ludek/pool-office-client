package pl.ludek.smat.home.pool_office_client.data.apiservice

import retrofit2.http.GET

interface ApiPoolInfoServiceMod {
    @GET("/pool-info")
   suspend fun getSensorData(): NetworkResult<PoolInfoData>
}