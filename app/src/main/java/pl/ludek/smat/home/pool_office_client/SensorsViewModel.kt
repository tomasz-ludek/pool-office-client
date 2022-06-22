package pl.ludek.smat.home.pool_office_client

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SensorsViewModel: ViewModel() {
     val currentSensorData: MutableLiveData<PoolInfoData> by lazy {
        MutableLiveData<PoolInfoData>()
            //.also {
           // loadPoolInfoData()
       // }
    }
    fun getPoolInfoData(): MutableLiveData<PoolInfoData>{
        return currentSensorData
    }
    fun loadPoolInfoData(){
        // load data
        currentSensorData.value = PoolInfoData(1.1f,1.1f,1.1f,1.1f)
        //println(PoolInfoService.create().getSensorData())
        //Log.d("DATA",PoolInfoService.create().getSensorData().toString())
    }

}


