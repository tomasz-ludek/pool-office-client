package pl.ludek.smat.home.pool_office_client


object Common {
    private const val BASE_URL = "http://0.0.0.0:8080/"
  // private const val BASE_URL = "http://localhost:8080/"
    val retrofitService: PoolInfoService
        get() = PoolInfoClient.getClient(BASE_URL).create(PoolInfoService::class.java)
}