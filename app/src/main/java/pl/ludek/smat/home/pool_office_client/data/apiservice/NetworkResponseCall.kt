package pl.ludek.smat.home.pool_office_client.data.apiservice

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

internal class NetworkResponseCall<R>(private val delegate: Call<R>) : Call<NetworkResult<R>> {
    private val errorCode = 1
    override fun enqueue(callback: Callback<NetworkResult<R>>) {
        return delegate.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        val baseResponse = body as BaseResponse
                        if (baseResponse.errorCode > 0) {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResult.Error(
                                        baseResponse.errorCode,
                                        "Error on the server"
                                    )
                                )
                            )
                        } else {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(NetworkResult.Success(body))
                            )
                        }
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResult.Error(
                                    errorCode,
                                    "Response is successful but the body is null"
                                )
                            )
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResult.Error(code, errorBody.toString()))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResult.Error(
                                    code,
                                    "Response is successful but the error body is null"
                                )
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResult.Exception<R>(throwable)
                    else -> NetworkResult.Exception<R>(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResult<R>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}