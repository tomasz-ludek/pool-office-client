package pl.ludek.smat.home.pool_office_client.data.live_data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData

class SensorsViewModel: ViewModel() {
    val currentSensorData: MutableLiveData<PoolInfoData> by lazy {
        MutableLiveData<PoolInfoData>()
    }
    fun setPoolInfoData(data: PoolInfoData){
          currentSensorData.value = data
    }
}