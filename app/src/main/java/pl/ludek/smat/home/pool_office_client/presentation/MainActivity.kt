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
    private lateinit var relayFirstOnStr:String
    private lateinit var  relayFirstOffStr:String
    private lateinit var relaySecondOnStr:String
    private lateinit var relaySecondOffStr:String
    private lateinit var relayThirdOnStr:String
    private lateinit var relayThirdOffStr:String
    private lateinit var relayForthOnStr:String
    private lateinit var relayForthOffStr:String
    private lateinit var relayFifthOnStr:String
    private lateinit var relayFifthOffStr:String
    private lateinit var relaySixthOnStr:String
    private lateinit var relaySixthOffStr:String
    private lateinit var relaySeventhOnStr:String
    private lateinit var relaySeventhOffStr:String
    private lateinit var relayEighthOnStr:String
    private lateinit var relayEighthOffStr:String
    private lateinit var relayAllOnStr:String
    private lateinit var relayAllOffStr:String
    private lateinit var errorRelayStr:String
    private lateinit var  errorSensorStr:String
    private lateinit var dataFromRepository: PoolInfoViewModel
    private lateinit var dataFromRelay:MainActivityViewModel
    private val dataFromSensor: SensorsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val dataFromSensorObserver = Observer<PoolInfoData>{ inputDataFromSensor ->
            if(inputDataFromSensor.p1 == 0.0f && inputDataFromSensor.t1 == 0.0f && inputDataFromSensor.t2 == 0.0f && inputDataFromSensor.t3 == 0.0f){
                 binding.sensorDataView.text = errorSensorStr
            }else {
                binding.sensorDataView.text =
                        " T1= " + inputDataFromSensor.t1 +
                        " T2= " + inputDataFromSensor.t2 +
                        " T3= " + inputDataFromSensor.t3 +
                        " P1= " + inputDataFromSensor.p1
            }
        }
        dataFromSensor.currentSensorData.observe(this,dataFromSensorObserver)
        dataFromRelay.setDataToLiveData(dataFromRepository, dataFromSensor, this)

        binding.relayOneOn.setOnClickListener {
            if( binding.relayOneOn.text == relayFirstOnStr){
                dataFromRelay.postOnRelayFirst().observe(this, Observer {
                    if(!it.errorRelay){ binding.relayOneOn.text = relayFirstOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOffRelayFirst().observe(this, Observer {
                    if(!it.errorRelay){binding.relayOneOn.text = relayFirstOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relaySecondOn.setOnClickListener {
            if( binding.relaySecondOn.text == relaySecondOnStr){
                dataFromRelay.postOnRelaySecond().observe(this, Observer {
                    if(!it.errorRelay){ binding.relaySecondOn.text = relaySecondOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOffRelaySecond().observe(this, Observer {
                    if(!it.errorRelay){binding.relaySecondOn.text = relaySecondOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relayThirdOn.setOnClickListener {
            if( binding.relayThirdOn.text == relayThirdOnStr){
                dataFromRelay.postOnRelayThird().observe(this, Observer {
                    if(!it.errorRelay){ binding.relayThirdOn.text = relayThirdOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOffRelayThird().observe(this, Observer {
                    if(!it.errorRelay){binding.relayThirdOn.text = relayThirdOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relayFourthOn.setOnClickListener {
            if( binding.relayFourthOn.text == relayForthOnStr){
                dataFromRelay.postOnRelayForth().observe(this, Observer {
                    if(!it.errorRelay){ binding.relayFourthOn.text = relayForthOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOffRelayForth().observe(this, Observer {
                    if(!it.errorRelay){binding.relayFourthOn.text = relayForthOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relayFieveOn.setOnClickListener {
            if(binding.relayFieveOn.text == relayFifthOnStr){
                dataFromRelay.postOnRelayFive().observe(this, Observer {
                    if (!it.errorRelay){
                        binding.relayFieveOn.text = relayFifthOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else{
                dataFromRelay.postOffRelayFive().observe(this, Observer {
                    if(!it.errorRelay){binding.relayFieveOn.text = relayFifthOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relaySixthOn.setOnClickListener {
            if( binding.relaySixthOn.text == relaySixthOnStr){
                dataFromRelay.postOnRelaySixth().observe(this, Observer {
                    if(!it.errorRelay){ binding.relaySixthOn.text = relaySixthOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOffRelaySixth().observe(this, Observer {
                    if(!it.errorRelay){binding.relaySixthOn.text = relaySixthOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relaySeventhOn.setOnClickListener {
            if( binding.relaySeventhOn.text == relaySeventhOnStr){
                dataFromRelay.postOnRelaySeventh().observe(this, Observer {
                    if(!it.errorRelay){ binding.relaySeventhOn.text = relaySeventhOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOffRelaySeventh().observe(this, Observer {
                    if(!it.errorRelay){binding.relaySeventhOn.text = relaySeventhOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relayEighthOn.setOnClickListener {
            if( binding.relayEighthOn.text == relayEighthOnStr){
                dataFromRelay.postOnRelayEight().observe(this, Observer {
                    if(!it.errorRelay){ binding.relayEighthOn.text = relayEighthOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else {
                dataFromRelay.postOffRelayEighth().observe(this, Observer {
                    if(!it.errorRelay){binding.relayEighthOn.text = relayEighthOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        binding.relayAllOn.setOnClickListener {
            if(binding.relayAllOn.text == relayAllOnStr){
                dataFromRelay.postOnRelayAll().observe(this, Observer {
                    if (!it.errorRelay){
                        binding.relayAllOn.text = relayAllOffStr
                        binding.relayOneOn.text = relayFirstOffStr
                        binding.relaySecondOn.text = relaySecondOffStr
                        binding.relayThirdOn.text = relayThirdOffStr
                        binding.relayFourthOn.text = relayForthOffStr
                        binding.relayFieveOn.text = relayFifthOffStr
                        binding.relaySixthOn.text = relaySixthOffStr
                        binding.relaySeventhOn.text = relaySeventhOffStr
                        binding.relayEighthOn.text = relayEighthOffStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }else{
                dataFromRelay.postOffRelayAll().observe(this, Observer {
                    if(!it.errorRelay){
                        binding.relayAllOn.text = relayAllOnStr
                        binding.relayOneOn.text = relayFirstOnStr
                        binding.relaySecondOn.text = relaySecondOnStr
                        binding.relayThirdOn.text = relayThirdOnStr
                        binding.relayFourthOn.text = relayForthOnStr
                        binding.relayFieveOn.text = relayFifthOnStr
                        binding.relaySixthOn.text = relaySixthOnStr
                        binding.relaySeventhOn.text = relaySeventhOnStr
                        binding.relayEighthOn.text = relayEighthOnStr
                    }else{
                        showToast(errorRelayStr)
                    }
                })
            }
        }
        setContentView(binding.root)
    }

    private fun initView(){
        relayFirstOnStr = getString(R.string.relay_1_on)
        relayFirstOffStr = getString(R.string.relay_1_off)
        relaySecondOnStr = getString(R.string.relay_second_on)
        relaySecondOffStr = getString(R.string.relay_second_off)
        relayThirdOnStr = getString(R.string.relay_third_on)
        relayThirdOffStr = getString(R.string.relay_third_off)
        relayForthOnStr = getString(R.string.relay_fourth_on)
        relayForthOffStr = getString(R.string.relay_fourth_off)
        relayFifthOnStr = getString(R.string.relay_5_on)
        relayFifthOffStr = getString(R.string.relay_5_off)
        relaySixthOnStr = getString(R.string.relay_sixth_on)
        relaySixthOffStr = getString(R.string.relay_sixth_off)
        relaySeventhOnStr = getString(R.string.relay_seventh_on)
        relaySeventhOffStr = getString(R.string.relay_seventh_off)
        relayEighthOnStr = getString(R.string.relay_eighth_on)
        relayEighthOffStr = getString(R.string.relay_eighth_off)
        relayAllOnStr = getString(R.string.relay_all_on)
        relayAllOffStr = getString(R.string.relay_all_off)
        errorRelayStr = getString(R.string.relay_error)
        errorSensorStr = getString(R.string.data_from_sensor)
        dataFromRepository = ViewModelProvider(this).get(PoolInfoViewModel::class.java)
        dataFromRelay = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private  fun showToast(text:String){
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}