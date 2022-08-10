package pl.ludek.smat.home.pool_office_client.data.apiservice

sealed class NetworkResult <R:Any>{
    class Success<R: Any>(val bodyData : Any) : NetworkResult<R>(){}
    class Error<R: Any>(val code : Int, val message: String?) : NetworkResult<R>(){}
    class Exception<R: Any>(val e: Throwable) : NetworkResult<R>(){}
}