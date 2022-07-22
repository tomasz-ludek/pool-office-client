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
    fun setFirstChannel(data: Boolean){
        val state = currentRelayState.value
        if (state != null) {
            currentRelayState.value = InitializationStateRelay(data,
                state.relaySecond,
                state.relayThird,
                state.relayFourth,
                state.relayFifth,
                state.relaySixth,
                state.relaySeventh,
                state.relayEighth,
                state.errorRelay)
        }
    }
    fun setSecondChannel(data: Boolean){
        val state = currentRelayState.value
        if (state != null) {
            currentRelayState.value = InitializationStateRelay(state.relayFirst,
                data,
                state.relayThird,
                state.relayFourth,
                state.relayFifth,
                state.relaySixth,
                state.relaySeventh,
                state.relayEighth,
                state.errorRelay)
        }
    }

}