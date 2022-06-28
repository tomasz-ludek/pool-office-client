//package pl.ludek.smat.home.pool_office_client.domain.repositories
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import pl.ludek.smat.home.pool_office_client.data.apiservice.ApiModule
//import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class PoolInfoRepository :RemoteSensorData {
//    var client = ApiModule.retrofitService
//
//    override fun getDataFromSensor(): MutableLiveData<PoolInfoData> {
//        var result = MutableLiveData<PoolInfoData>()
//
//        client.getSensorData().enqueue(object : Callback<PoolInfoData> {
//            override fun onResponse(call: Call<PoolInfoData>, response: Response<PoolInfoData>) {
//                if(response.isSuccessful){
//                    result.value = response.body()
//                    Log.d("LOG", response.body().toString())
//                }
//            }
//            override fun onFailure(call: Call<PoolInfoData>, t: Throwable) {
//                Log.d("LOG", t.message.toString())
//            }
//        })
//        return result
//    }
//}