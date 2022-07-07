package pl.ludek.smat.home.pool_office_client.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.ludek.smat.home.pool_office_client.R
import pl.ludek.smat.home.pool_office_client.data.live_data.SensorsViewModel
import pl.ludek.smat.home.pool_office_client.databinding.ActivityMainBinding
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData

class MainActivity : AppCompatActivity() {
    private lateinit var relayFirstOnStr:String    // "RELAY-FIRST-ON"
    private lateinit var  relayFirstOffStr:String  // "RELAY-FIRST-OFF"
    private lateinit var relayFiveOnStr:String    // "RELAY-FIVE-ON"
    private lateinit var relayFiveOffStr:String  // "RELAY-FIVE-OFF"
    private lateinit var relayAllOnStr:String    // "RELAY-ALL-ON"
    private lateinit var relayAllOffStr:String   // "RELAY-ALL-OFF"
    private lateinit var errorRelayStr:String
    private lateinit var  errorSensorStr:String
    private lateinit var dataFromRepository: PoolInfoViewModel
    private lateinit var dataFromRelay: RelayViewModel
    private val dataFromSensor: SensorsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val dataFromSensorObserver = Observer<PoolInfoData>{ inputDataFromSensor ->
            if(inputDataFromSensor.p1 == 0.0f && inputDataFromSensor.t1 == 0.0f && inputDataFromSensor.t2 == 0.0f && inputDataFromSensor.t3 == 0.0f){
                 binding.sensorDataView.text = errorSensorStr
            }else {
                val rez:String =" T1= " + inputDataFromSensor.t1 +
                        " T2= " + inputDataFromSensor.t2 +
                        " T3= " + inputDataFromSensor.t3 +
                        " P1= " + inputDataFromSensor.p1
                binding.sensorDataView.text = rez
            }
        }
        dataFromSensor.currentSensorData.observe(this,dataFromSensorObserver)
        setDataToLiveData()
        binding.relayOneOn.setOnClickListener {
            if( binding.relayOneOn.text == relayFirstOnStr){
                dataFromRelay.postOneRelayOn().observe(this, Observer {
                    if(!it.errorRelay){ binding.relayOneOn.text = relayFirstOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOneRelayOff().observe(this, Observer {
                    if(!it.errorRelay){binding.relayOneOn.text = relayFirstOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relayFieveOn.setOnClickListener {
            if(binding.relayFieveOn.text == relayFiveOnStr){
                dataFromRelay.postFiveRelayOn().observe(this, Observer {
                    if (!it.errorRelay){
                        binding.relayFieveOn.text = relayFiveOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else{
                dataFromRelay.postFiveRelayOff().observe(this, Observer {
                    if(!it.errorRelay){binding.relayFieveOn.text = relayFiveOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relayAllOn.setOnClickListener {
            if(binding.relayAllOn.text == relayAllOnStr){
                dataFromRelay.postAllRelayOn().observe(this, Observer {
                    if (!it.errorRelay){
                        binding.relayAllOn.text = relayAllOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else{
                dataFromRelay.postAllRelayOff().observe(this, Observer {
                    if(!it.errorRelay){binding.relayAllOn.text = relayAllOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        setContentView(binding.root)
    }

    private fun setDataToLiveData(){
         dataFromRepository.getDataFromLocalStorage().observe(this, Observer {
             dataFromSensor.setPoolInfoData(it)
         })
     }

    private fun initView(){
        relayFirstOnStr = getString(R.string.relay_1_on)
        relayFirstOffStr = getString(R.string.relay_1_off)
        relayFiveOnStr = getString(R.string.relay_5_on)
        relayFiveOffStr = getString(R.string.relay_5_off)
        relayAllOnStr = getString(R.string.relay_all_on)
        relayAllOffStr = getString(R.string.relay_all_off)
        errorRelayStr = getString(R.string.relay_error)
        errorSensorStr = getString(R.string.data_from_sensor)
        dataFromRepository = ViewModelProvider(this).get(PoolInfoViewModel::class.java)
        dataFromRelay = ViewModelProvider(this).get(RelayViewModel::class.java)
    }

    private  fun showToast(text:String){
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}