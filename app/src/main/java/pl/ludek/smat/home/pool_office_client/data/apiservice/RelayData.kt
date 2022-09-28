package pl.ludek.smat.home.pool_office_client.data.apiservice

data class RelayData(val relayNumber: Int, val stateRelay: Boolean, override val errorCode: Int) :
    BaseResponse