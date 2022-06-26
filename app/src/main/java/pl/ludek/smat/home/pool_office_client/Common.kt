package pl.ludek.smat.home.pool_office_client

object Common {
   private const val BASE_URL = "http://10.0.2.2:8080/"
    val retrofitService: PoolInfoService
        get() = PoolInfoClient.getClient(BASE_URL).create(PoolInfoService::class.java)
}