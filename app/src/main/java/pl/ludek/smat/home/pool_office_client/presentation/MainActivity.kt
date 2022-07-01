package pl.ludek.smat.home.pool_office_client.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.ludek.smat.home.pool_office_client.R
import pl.ludek.smat.home.pool_office_client.data.live_data.SensorsViewModel
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var relayFirstOnStr:String    // "RELAY-FIRST-ON"
    private lateinit var  relayFirstOffStr:String  // "RELAY-FIRST-OFF"
    private lateinit var relayFiveOnStr:String    // "RELAY-FIVE-ON"
    private lateinit var relayFiveOffStr:String  // "RELAY-FIVE-OFF"
    private lateinit var relayAllOnStr:String    // "RELAY-ALL-ON"
    private lateinit var relayAllOffStr:String   // "RELAY-ALL-OFF"
    private lateinit var errorRelayStr:String
    private lateinit var dataFromRepository: PoolInfoViewModel
    private lateinit var dataFromRelay: RelayViewModel
    private val dataFromSensor: SensorsViewModel by viewModels()
    private lateinit var sensorDataTextView: TextView
    private lateinit var relayFirstOnButton:Button
    private lateinit var relayFiveOnButton: Button
    private lateinit var relayAllOnButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        relayFirstOnStr = getString(R.string.relay_1_on)
        relayFirstOffStr = getString(R.string.relay_1_off)
        relayFiveOnStr = getString(R.string.relay_5_on)
        relayFiveOffStr = getString(R.string.relay_5_off)
        relayAllOnStr = getString(R.string.relay_all_on)
        relayAllOffStr = getString(R.string.relay_all_off)
        errorRelayStr = getString(R.string.relay_error)
        relayFirstOnButton = findViewById(R.id.relayOneOn)
        relayFiveOnButton = findViewById(R.id.relayFieveOn)
        relayAllOnButton = findViewById(R.id.relayAllOn)
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
        relayFiveOnButton.setOnClickListener(this)
        relayAllOnButton.setOnClickListener(this)
    }

     private fun setDataToLiveData(){
         dataFromRepository.getDataFromLocalStorage().observe(this, Observer {
             dataFromSensor.setPoolInfoData(it)
         })
     }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.relayOneOn ->if(relayFirstOnButton.text == relayFirstOnStr){
                    dataFromRelay.postOneRelayOn().observe(this, Observer {
                    if(!it.errorRelay){ relayFirstOnButton.text = relayFirstOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOneRelayOff().observe(this, Observer {
                    if(!it.errorRelay){relayFirstOnButton.text = relayFirstOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }

            R.id.relayFieveOn -> if(relayFiveOnButton.text == relayFiveOnStr){
                dataFromRelay.postFiveRelayOn().observe(this, Observer {
                    if (!it.errorRelay){
                        relayFiveOnButton.text = relayFiveOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else{
                dataFromRelay.postFiveRelayOff().observe(this, Observer {
                    if(!it.errorRelay){relayFiveOnButton.text = relayFiveOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }

            R.id.relayAllOn -> if(relayAllOnButton.text == relayAllOnStr){
                dataFromRelay.postAllRelayOn().observe(this, Observer {
                    if (!it.errorRelay){
                        relayAllOnButton.text = relayAllOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else{
                dataFromRelay.postAllRelayOff().observe(this, Observer {
                    if(!it.errorRelay){relayAllOnButton.text = relayAllOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
    }

    private  fun showToast(text:String){
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}



