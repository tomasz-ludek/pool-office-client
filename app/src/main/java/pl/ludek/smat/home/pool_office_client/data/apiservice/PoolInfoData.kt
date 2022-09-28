package pl.ludek.smat.home.pool_office_client.data.apiservice

data class PoolInfoData(
    var t1: Float,
    var t2: Float,
    var t3: Float,
    var p1: Float,
    override val errorCode: Int
) : BaseResponse