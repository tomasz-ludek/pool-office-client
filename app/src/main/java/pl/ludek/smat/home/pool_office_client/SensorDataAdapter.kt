package pl.ludek.smat.home.pool_office_client

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SensorDataAdapter {
     fun loadDataFromSensor(){
        val data = PoolInfoInterface.create().getSensorData()
        data.enqueue(object : Callback<PoolInfoData> {
            override fun onResponse(
                call: Call<PoolInfoData>,
                response: Response<PoolInfoData>
            ) {
                if( response?.body() != null){
                    Log.d("LOG", response?.body().toString())
                    SensorsViewModel().loadPoolInfoData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PoolInfoData>, t: Throwable) {
                ///  TODO("Not yet implemented")
                Log.d("LOG", t.message.toString())

            }
        })
    }
}