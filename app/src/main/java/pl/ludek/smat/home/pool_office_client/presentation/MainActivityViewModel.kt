package pl.ludek.smat.home.pool_office_client.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.ludek.smat.home.pool_office_client.data.apiservice.PoolInfoClient
import pl.ludek.smat.home.pool_office_client.data.apiservice.InitializationStateRelay
import pl.ludek.smat.home.pool_office_client.data.apiservice.PoolInfoData
import pl.ludek.smat.home.pool_office_client.data.apiservice.RelayData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val TAG = MainActivityViewModel::class.java.simpleName
const val RELAY_ID_ALL = 256

class MainActivityViewModel : ViewModel() {

    private val poolInfoClient = PoolInfoClient
    val singleRelayStateData = MutableLiveData<RelayData>()
    val completeRelayStateData = MutableLiveData<Array<Boolean>>()

    val poolInfoData = MutableLiveData<PoolInfoData>()

    fun updatePoolInfo() {
        poolInfoClient.getSensorData().enqueue(object : Callback<PoolInfoData> {
            override fun onResponse(call: Call<PoolInfoData>, response: Response<PoolInfoData>) {
                if (response.isSuccessful) {
                    poolInfoData.value = response.body()
                } else {
                    poolInfoData.value = null
                }
            }

            override fun onFailure(call: Call<PoolInfoData>, t: Throwable) {
                poolInfoData.value = null
                Log.d(TAG, t.message.toString())
            }
        })
    }

    fun switchRelay(relayId: Int, relayState: Boolean) {

        poolInfoClient.switchRelay(relayId, relayState).enqueue(object : Callback<RelayData> {

            override fun onResponse(call: Call<RelayData>, response: Response<RelayData>) {
                if (response.isSuccessful) {
                    singleRelayStateData.value = response.body()
                } else {
                    singleRelayStateData.value = RelayData(relayId, !relayState, errorRelay = true)
                }
            }

            override fun onFailure(call: Call<RelayData>, t: Throwable) {
                singleRelayStateData.value = RelayData(relayId, !relayState, errorRelay = true)
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