package pl.ludek.smat.home.pool_office_client.data.apiservice

data class InitializationStateRelay(val relayAnswer: Array<Boolean>, override val errorCode: Int) :
    BaseResponse