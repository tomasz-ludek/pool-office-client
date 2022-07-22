package pl.ludek.smat.home.pool_office_client.domain.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pl.ludek.smat.home.pool_office_client.data.apiservice.PoolInfoClient
import pl.ludek.smat.home.pool_office_client.domain.model.InitializationStateRelay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetInitialStateRelay: SingleUseCase<InitializationStateRelay>{
    private var client = PoolInfoClient
    override fun execute(): MutableLiveData<InitializationStateRelay> {
        var result = MutableLiveData<InitializationStateRelay>()
        client.getInitializationStateRelay().enqueue(object : Callback<InitializationStateRelay>{
            override fun onResponse(
                call: Call<InitializationStateRelay>,
                response: Response<InitializationStateRelay>
            ) {
                if(response.isSuccessful){
                    result.value = response.body()
                }
            }
            override fun onFailure(call: Call<InitializationStateRelay>, t: Throwable) {
                Log.d("LOG", t.message.toString())
            }
        })
        return result
    }
}