package pl.ludek.smat.home.pool_office_client.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.InitializationStateRelay
import pl.ludek.smat.home.pool_office_client.domain.usecases.GetInitialStateRelay

class InitializationStateRelayViewModel: ViewModel() {
    private val initializationData: GetInitialStateRelay = GetInitialStateRelay()

    fun getInitializationDataRelay(): MutableLiveData<InitializationStateRelay> {
        return initializationData.execute()
    }
}