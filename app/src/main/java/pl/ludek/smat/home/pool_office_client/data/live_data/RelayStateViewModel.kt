package pl.ludek.smat.home.pool_office_client.data.live_data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.InitializationStateRelay

class RelayStateViewModel: ViewModel() {
    val currentRelayState: MutableLiveData<InitializationStateRelay> by lazy {
        MutableLiveData<InitializationStateRelay>()
    }
    fun setRelayState(data: InitializationStateRelay){
        currentRelayState.value = data
    }
}