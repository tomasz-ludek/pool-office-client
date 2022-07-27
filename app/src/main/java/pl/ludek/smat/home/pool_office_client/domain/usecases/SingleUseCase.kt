package pl.ludek.smat.home.pool_office_client.domain.usecases

import androidx.lifecycle.MutableLiveData

interface SingleUseCase <R> {
    fun execute(): MutableLiveData<R>
}