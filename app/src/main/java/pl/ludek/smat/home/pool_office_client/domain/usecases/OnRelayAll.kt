package pl.ludek.smat.home.pool_office_client.domain.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pl.ludek.smat.home.pool_office_client.data.apiservice.ApiModule
import pl.ludek.smat.home.pool_office_client.domain.model.RelayData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnRelayAll:SingleUseCaseButton {
    private var client = ApiModule.retrofitService
    override fun execute(): MutableLiveData<RelayData> {
        var result = MutableLiveData<RelayData>()
        client.postRelayAllOn().enqueue(object : Callback<RelayData> {
            override fun onResponse(call: Call<RelayData>, response: Response<RelayData>) {
                if(response.isSuccessful){
                    result.value = response.body()
                }
            }

            override fun onFailure(call: Call<RelayData>, t: Throwable) {
                Log.d("LOG", t.message.toString())
            }
        })
        return result
    }
}