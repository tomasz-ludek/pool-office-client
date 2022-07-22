package pl.ludek.smat.home.pool_office_client.domain.usecases

import androidx.lifecycle.MutableLiveData
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData

interface SingleUseCase <R> {
    fun execute(): MutableLiveData<R>
}