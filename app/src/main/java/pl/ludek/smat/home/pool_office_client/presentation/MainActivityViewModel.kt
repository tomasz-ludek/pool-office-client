package pl.ludek.smat.home.pool_office_client.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.ludek.smat.home.pool_office_client.data.apiservice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val TAG = MainActivityViewModel::class.java.simpleName
const val RELAY_ID_ALL = 256

class MainActivityViewModel : ViewModel() {

    private val poolInfoClient = PoolInfoClient
    val singleRelayStateData = MutableLiveData<RelayData>()
    val completeRelayStateData = MutableLiveData<Array<Boolean>>()
    val poolInfoData =MutableLiveData<NetworkResult<PoolInfoData>>()
    private val poolInfoClientMod = PoolInfoClientMod

    fun updatePoolInfo() {
        GlobalScope.launch {
            poolInfoData.postValue(poolInfoClientMod.getSensorData())
        }
    }

    fun switchRelay(relayId: Int, relayState: Boolean) {

        poolInfoClient.switchRelay(relayId, relayState).enqueue(object : Callback<RelayData> {

            override fun onResponse(call: Call<RelayData>, response: Response<RelayData>) {
                if (response.isSuccessful) {
                    singleRelayStateData.value = response.body()
                } else {
                    singleRelayStateData.value = RelayData(relayId, !relayState, errorCode = 1)
                }
            }

            override fun onFailure(call: Call<RelayData>, t: Throwable) {
                singleRelayStateData.value = RelayData(relayId, !relayState, errorCode = 1)
                Log.d(TAG, t.message.toString())
            }
        })
    }

    fun updateCompleteRelayState() {
        poolInfoClient.getInitializationStateRelay()
            .enqueue(object : Callback<InitializationStateRelay> {
                override fun onResponse(
                    call: Call<InitializationStateRelay>,
                    response: Response<InitializationStateRelay>
                ) {
                    if (response.isSuccessful) {
                        completeRelayStateData.value = response.body()!!.relayAnswer
                    } else {
                        completeRelayStateData.value = null
                    }
                }

                override fun onFailure(call: Call<InitializationStateRelay>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                    completeRelayStateData.value = null
                }
            })
    }

}