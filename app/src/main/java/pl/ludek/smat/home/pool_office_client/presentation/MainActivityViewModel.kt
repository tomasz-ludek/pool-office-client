package pl.ludek.smat.home.pool_office_client.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        viewModelScope.launch {
            poolInfoData.postValue(poolInfoClient.getSensorData())
        }
    }

    fun switchRelay(relayId: Int, relayState: Boolean) {
        viewModelScope.launch {
           singleRelayStateData.postValue(poolInfoClient.switchRelay(relayId, relayState))
        }
    }

    fun updateCompleteRelayState() {
        viewModelScope.launch {
           completeRelayStateData.postValue(poolInfoClient.getInitializationStateRelay())
        }
    }
}