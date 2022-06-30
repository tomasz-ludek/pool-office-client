package pl.ludek.smat.home.pool_office_client.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.ludek.smat.home.pool_office_client.R
import pl.ludek.smat.home.pool_office_client.data.live_data.SensorsViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var dataFromRepository: PoolInfoViewModel
    private lateinit var dataFromRelay: RelayViewModel
    private val dataFromSensor: SensorsViewModel by viewModels()
    private lateinit var sensorDataTextView: TextView
    private lateinit var relayFirstOnButton:Button
    private lateinit var relayFirstOffButton:Button
    private lateinit var relayFiveOnButton: Button
    private lateinit var relayFiveOffButton: Button
    private lateinit var relayAllOnButton: Button
    private lateinit var relayAllOffButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        relayFirstOnButton = findViewById(R.id.relayOneOn)
        relayFirstOffButton = findViewById(R.id.relayOneOff)
        relayFiveOnButton = findViewById(R.id.relayFieveOn)
        relayFiveOffButton = findViewById(R.id.relayFieveOff)
        relayAllOnButton = findViewById(R.id.relayAllOn)
        relayAllOffButton = findViewById(R.id.relayAllOff)
        dataFromRepository = ViewModelProvider(this).get(PoolInfoViewModel::class.java)
        dataFromRelay = ViewModelProvider(this).get(RelayViewModel::class.java)
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
        relayFirstOnButton.setOnClickListener(this)
        relayFirstOffButton.setOnClickListener(this)
        relayFiveOnButton.setOnClickListener(this)
        relayFiveOffButton.setOnClickListener(this)
        relayAllOnButton.setOnClickListener(this)
        relayAllOffButton.setOnClickListener(this)
    }

     private fun setDataToLiveData(){
         dataFromRepository.getDataFromLocalStorage().observe(this, Observer {
             dataFromSensor.setPoolInfoData(it)
         })
     }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.relayOneOn -> dataFromRelay.postOneRelayOn().observe(this, Observer { println(it) })
            R.id.relayOneOff -> dataFromRelay.postOneRelayOff().observe(this, Observer { println(it) })
            R.id.relayFieveOn -> dataFromRelay.postFiveRelayOn().observe(this, Observer { println(it) })
            R.id.relayFieveOff -> dataFromRelay.postFiveRelayOff().observe(this, Observer { println(it) })
            R.id.relayAllOn -> dataFromRelay.postAllRelayOn().observe(this, Observer { println(it) })
            R.id.relayAllOff -> dataFromRelay.postAllRelayOff().observe(this, Observer { println(it) })
        }
    }
}



