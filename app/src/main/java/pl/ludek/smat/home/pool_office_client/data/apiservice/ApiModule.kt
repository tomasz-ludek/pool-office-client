package pl.ludek.smat.home.pool_office_client.data.apiservice

object ApiModule  {
   private const val BASE_URL = "http://10.0.2.2:8080/"

    val retrofitService: ApiPoolInfoService
        get() = PoolInfoClientNetworkModule.getClient(BASE_URL).create(ApiPoolInfoService::class.java)
}


