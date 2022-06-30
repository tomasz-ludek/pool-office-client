package pl.ludek.smat.home.pool_office_client.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.RelayData
import pl.ludek.smat.home.pool_office_client.domain.usecases.*

class RelayViewModel: ViewModel() {
    private val relayOneOn = OnRelayFirst()
    private val relayOneOff = OffRelayFirst()
    private val relayFiveOn = OnRelayFive()
    private val relayFiveOff = OffRelayFive()
    private val relayAllOn = OnRelayAll()
    private val relayAllOff = OffRelayAll()

    fun postOneRelayOn():LiveData<RelayData>{
        return relayOneOn.execute()
    }
    fun postOneRelayOff():LiveData<RelayData>{
        return relayOneOff.execute()
    }

    fun postFiveRelayOn():LiveData<RelayData>{
        return  relayFiveOn.execute()
    }

    fun postFiveRelayOff():LiveData<RelayData>{
        return relayFiveOff.execute()
    }

    fun postAllRelayOn():LiveData<RelayData>{
        return  relayAllOn.execute()
    }

    fun postAllRelayOff():LiveData<RelayData>{
        return relayAllOff.execute()
    }
}