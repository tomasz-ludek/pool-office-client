package pl.ludek.smat.home.pool_office_client.domain.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pl.ludek.smat.home.pool_office_client.data.apiservice.PoolInfoClient
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDataFromSensorUseCase (): SingleUseCase<PoolInfoData> {
    private var client = PoolInfoClient
    override fun execute(): MutableLiveData<PoolInfoData> {
        var result = MutableLiveData<PoolInfoData>()
        client.getSensorData().enqueue(object : Callback<PoolInfoData> {
            override fun onResponse(call: Call<PoolInfoData>, response: Response<PoolInfoData>) {
                if(response.isSuccessful){
                    result.value = response.body()
                }
            }
            override fun onFailure(call: Call<PoolInfoData>, t: Throwable) {
                Log.d("LOG", t.message.toString())
            }
        })
        return result
    }
}