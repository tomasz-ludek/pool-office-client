package pl.ludek.smat.home.pool_office_client.data.apiservice

sealed class NetworkResult<R> {
    class Success<R>(val bodyData: Any) : NetworkResult<R>()
    class Error<R>(val code: Int, val message: String?) : NetworkResult<R>()
    class Exception<R>(val e: Throwable) : NetworkResult<R>()
}