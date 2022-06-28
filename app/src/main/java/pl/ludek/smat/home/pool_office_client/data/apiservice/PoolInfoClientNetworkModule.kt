package pl.ludek.smat.home.pool_office_client.data.apiservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PoolInfoClientNetworkModule {
    private var retrofit: Retrofit?= null
    fun getClient(baseUrl: String):Retrofit{
        if( retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}