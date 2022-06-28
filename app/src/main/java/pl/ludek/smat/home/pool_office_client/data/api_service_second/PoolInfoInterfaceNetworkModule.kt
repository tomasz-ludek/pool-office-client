//package pl.ludek.smat.home.pool_office_client.data.api_service_second
//
//import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData
//import retrofit2.Call
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//
//interface PoolInfoInterfaceNetworkModule {
//    @GET("pool-info")
//    open fun getSensorData(): Call<PoolInfoData>
//
//    companion object{
//        var baseUrl = "http://10.0.2.2:8080/"
//        fun create(): PoolInfoInterfaceNetworkModule {
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(baseUrl)
//                .build()
//            return retrofit.create(PoolInfoInterfaceNetworkModule::class.java)
//        }
//    }
//}