package pl.ludek.smat.home.pool_office_client

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SensorsViewModel: ViewModel() {
     val currentSensorData: MutableLiveData<PoolInfoData> by lazy {
        MutableLiveData<PoolInfoData>()
    }
    fun getPoolInfoData(): MutableLiveData<PoolInfoData>{
        return currentSensorData
    }
    fun loadPoolInfoData(data: PoolInfoData){
          currentSensorData.value = data
    }

}


