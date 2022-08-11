package pl.ludek.smat.home.pool_office_client.data.apiservice

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class DataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            return null
        }
        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResult<<R>>"
        }
        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not NetworkResult then we can't handle this type, so we return null
        if (getRawType(responseType) != NetworkResult::class.java) {
            return null
        }
        // the response type is ApiResponse and should be parameterized
        check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResult<R>" }

        val successBodyType = getParameterUpperBound(0, responseType)

        return DataCallAdapter<Any>(successBodyType)
    }
}