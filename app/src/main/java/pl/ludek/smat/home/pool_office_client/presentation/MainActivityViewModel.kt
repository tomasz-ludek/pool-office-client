package pl.ludek.smat.home.pool_office_client.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import pl.ludek.smat.home.pool_office_client.data.apiservice.PoolInfoClient
import pl.ludek.smat.home.pool_office_client.data.live_data.SensorsViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.RelayData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val relayStateOn:Boolean = true
    private val relayStateOff:Boolean = false
    private val relayIdFirst: Int = 0
    private val relayIdFive: Int = 5
    private val relayIdAll: Int = 256

     fun setDataToLiveData(
         dataFromRepository: PoolInfoViewModel,
         dataFromSensor: SensorsViewModel,
         mainActivity: MainActivity
     ) {
        dataFromRepository.getDataFromLocalStorage().observe(mainActivity, Observer {
            dataFromSensor.setPoolInfoData(it)
        })
    }

     fun postOffRelayAll(): MutableLiveData<RelayData> {
        return receiveDataFromRelay(relayIdAll,relayStateOff)
    }

     fun postOffRelayFirst(): MutableLiveData<RelayData> {
        return receiveDataFromRelay(relayIdFirst,relayStateOff)
    }

     fun postOffRelayFive(): MutableLiveData<RelayData> {
        return receiveDataFromRelay(relayIdFive,relayStateOff)
    }

     fun postOnRelayAll(): MutableLiveData<RelayData> {
        return receiveDataFromRelay(relayIdAll,relayStateOn)
    }

     fun postOnRelayFirst(): MutableLiveData<RelayData> {
        return receiveDataFromRelay(relayIdFirst,relayStateOn)
    }

     fun postOnRelayFive(): MutableLiveData<RelayData> {
        return receiveDataFromRelay(relayIdFive,relayStateOn)
    }

    private fun receiveDataFromRelay(relayId:Int, relayState:Boolean): MutableLiveData<RelayData> {
        var client = PoolInfoClient
        var result = MutableLiveData<RelayData>()
        client.switchRelay(relayId,relayState).enqueue(object : Callback<RelayData> {
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