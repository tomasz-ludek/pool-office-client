package pl.ludek.smat.home.pool_office_client.data.apiservice

import retrofit2.*
import java.lang.reflect.Type

class DataCallAdapter <R : Any>(private val responseType: Type) :
    CallAdapter<R, Call<NetworkResult<R>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>):Call<NetworkResult<R>>  {
        return NetworkResponseCall(call)
    }
}