package pl.ludek.smat.home.pool_office_client.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.ludek.smat.home.pool_office_client.R
import pl.ludek.smat.home.pool_office_client.data.live_data.SensorsViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData

class MainActivity : AppCompatActivity() {
    private lateinit var dataFromRepository: PoolInfoViewModel
    private val dataFromSensor: SensorsViewModel by viewModels()
    private lateinit var sensorDataTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      dataFromRepository = ViewModelProvider(this).get(PoolInfoViewModel::class.java)
        sensorDataTextView = findViewById(R.id.sensorDataView)
        val dataFromSensorObserver = Observer<PoolInfoData>{ inputDataFromSensor ->
            val rez:String =" T1= " + inputDataFromSensor.t1 +
                            " T2= " + inputDataFromSensor.t2 +
                            " T3= " + inputDataFromSensor.t3 +
                            " P1= " + inputDataFromSensor.p1
            sensorDataTextView.text = rez
        }

        dataFromSensor.currentSensorData.observe(this,dataFromSensorObserver)
        setDataToLiveData()
    }

     private fun setDataToLiveData(){
         dataFromRepository.getDataFromLocalStorage().observe(this, Observer {
             dataFromSensor.setPoolInfoData(it)
         })
     }
}



