package pl.ludek.smat.home.pool_office_client.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.ludek.smat.home.pool_office_client.data.apiservice.*

private val TAG = MainActivityViewModel::class.java.simpleName
const val RELAY_ID_ALL = 256

class MainActivityViewModel : ViewModel() {

    private val poolInfoClient = PoolInfoClient
    val singleRelayStateData = MutableLiveData<NetworkResult<RelayData>>()
    val completeRelayStateData = MutableLiveData<NetworkResult<InitializationStateRelay>>()
    val poolInfoData =MutableLiveData<NetworkResult<PoolInfoData>>()

    fun updatePoolInfo() {
        GlobalScope.launch {
            poolInfoData.postValue(poolInfoClient.getSensorData())
        }
    }

    fun switchRelay(relayId: Int, relayState: Boolean) {
        GlobalScope.launch {
           singleRelayStateData.postValue(poolInfoClient.switchRelay(relayId, relayState))
        }
    }

    fun updateCompleteRelayState() {
        GlobalScope.launch {
           completeRelayStateData.postValue(poolInfoClient.getInitializationStateRelay())
        }
    }
}