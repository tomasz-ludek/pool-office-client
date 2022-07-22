package pl.ludek.smat.home.pool_office_client.domain.model

data class InitializationStateRelay(
    var relayFirst:Boolean,
    val relaySecond:Boolean,
    val relayThird:Boolean,
    val relayFourth:Boolean,
    val relayFifth:Boolean,
    val relaySixth:Boolean,
    val relaySeventh:Boolean,
    val relayEighth:Boolean,
    val errorRelay:Boolean)
