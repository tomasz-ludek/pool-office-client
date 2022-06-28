package pl.ludek.smat.home.pool_office_client.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData
import pl.ludek.smat.home.pool_office_client.domain.usecases.GetDataFromSensorUseCase

class PoolInfoViewModel : ViewModel(){
    private val dataPoolInfo: GetDataFromSensorUseCase = GetDataFromSensorUseCase()

    fun getDataFromLocalStorage():LiveData<PoolInfoData>{
        return dataPoolInfo.execute()
    }
}