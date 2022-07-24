package pl.ludek.smat.home.pool_office_client.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.ludek.smat.home.pool_office_client.R
import pl.ludek.smat.home.pool_office_client.data.live_data.RelayStateViewModel
import pl.ludek.smat.home.pool_office_client.data.live_data.SensorsViewModel
import pl.ludek.smat.home.pool_office_client.databinding.ActivityMainBinding
import pl.ludek.smat.home.pool_office_client.domain.model.InitializationStateRelay
import pl.ludek.smat.home.pool_office_client.domain.model.PoolInfoData

class MainActivity : AppCompatActivity() {
    private lateinit var errorRelayStr:String
    private lateinit var  errorSensorStr:String
    private var launchPermission: Boolean = true
    private lateinit var dataFromRepository: PoolInfoViewModel
    private lateinit var dataFromRelay:MainActivityViewModel
    private lateinit var dataInitialRelay: InitializationStateRelayViewModel
    private val dataFromSensor: SensorsViewModel by viewModels()
    private val relayState: RelayStateViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
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
        val relayStateObserver = Observer<InitializationStateRelay> { inputRelayState ->
            if(inputRelayState.errorRelay){
                binding.relayFirst.isEnabled = false
                binding.relaySecond.isEnabled = false
                binding.relayThird.isEnabled = false
                binding.relayFourth.isEnabled = false
                binding.relayFifth.isEnabled = false
                binding.relaySixth.isEnabled = false
                binding.relaySeventh.isEnabled = false
                binding.relayEighth.isEnabled = false
                binding.relayAllOn.isEnabled = false
                binding.relayAllOff.isEnabled = false
            }else{
                if (launchPermission){
                    launchPermission = false
                    binding.relayFirst.isChecked = inputRelayState.relayFirst
                    binding.relaySecond.isChecked = inputRelayState.relaySecond
                    binding.relayThird.isChecked = inputRelayState.relayThird
                    binding.relayFourth.isChecked = inputRelayState.relayFourth
                    binding.relayFifth.isChecked = inputRelayState.relayFifth
                    binding.relaySixth.isChecked = inputRelayState.relaySixth
                    binding.relaySeventh.isChecked = inputRelayState.relaySeventh
                    binding.relayEighth.isChecked = inputRelayState.relayEighth
                }
                launchPermission = true
            }
        }
        dataFromSensor.currentSensorData.observe(this,dataFromSensorObserver)
        dataFromRelay.setDataToLiveData(dataFromRepository, dataFromSensor, this)

        relayState.currentRelayState.observe(this,relayStateObserver)
        dataInitialRelay.getInitializationDataRelay().observe(this, Observer{
            relayState.setRelayState(it)
        })

        binding.relayFirst.setOnCheckedChangeListener { compoundButton, b ->
            if(launchPermission){
                if(b){
                    dataFromRelay.postOnRelayFirst().observe(this, Observer {
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }else{
                    dataFromRelay.postOffRelayFirst().observe(this,Observer{
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }
            }
        }

        binding.relaySecond.setOnCheckedChangeListener { compoundButton, b ->
            if(launchPermission){
                if(b){
                    dataFromRelay.postOnRelaySecond().observe(this, Observer {
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }else{
                    dataFromRelay.postOffRelaySecond().observe(this,Observer{
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }
            }
        }

        binding.relayThird.setOnCheckedChangeListener { compoundButton, b ->
            if(launchPermission){
                if(b){
                    dataFromRelay.postOnRelayThird().observe(this, Observer {
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }else{
                    dataFromRelay.postOffRelayThird().observe(this,Observer{
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }
            }
        }

        binding.relayFourth.setOnCheckedChangeListener { compoundButton, b ->
            if(launchPermission){
                if(b){
                    dataFromRelay.postOnRelayForth().observe(this, Observer {
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }else{
                    dataFromRelay.postOffRelayForth().observe(this,Observer{
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }
            }
        }

        binding.relayFifth.setOnCheckedChangeListener { compoundButton, b ->
            if(launchPermission){
                if(b){
                    dataFromRelay.postOnRelayFive().observe(this, Observer {
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }else{
                    dataFromRelay.postOffRelayFive().observe(this,Observer{
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }
            }
        }

        binding.relaySixth.setOnCheckedChangeListener { compoundButton, b ->
            if(launchPermission){
                if(b){
                    dataFromRelay.postOnRelaySixth().observe(this, Observer {
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }else{
                    dataFromRelay.postOffRelaySixth().observe(this,Observer{
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }
            }
        }

        binding.relaySeventh.setOnCheckedChangeListener { compoundButton, b ->
            if(launchPermission){
                if(b){
                    dataFromRelay.postOnRelaySeventh().observe(this, Observer {
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }else{
                    dataFromRelay.postOffRelaySeventh().observe(this,Observer{
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }
            }
        }

        binding.relayEighth.setOnCheckedChangeListener { compoundButton, b ->
            if(launchPermission){
                if(b){
                    dataFromRelay.postOnRelayEight().observe(this, Observer {
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }else{
                    dataFromRelay.postOffRelayEighth().observe(this,Observer{
                        if(it.errorRelay){
                            closingSwitch(compoundButton)
                        }
                    })
                }
            }
        }

        binding.relayAllOn.setOnClickListener {
            dataFromRelay.postOnRelayAll().observe(this,Observer{
                if(it.errorRelay){
                    binding.relayAllOn.isEnabled = false
                    showToast(errorRelayStr)
                }else{
                    switchingAllSwitch(true)
                }
            })
        }

        binding.relayAllOff.setOnClickListener {
            dataFromRelay.postOffRelayAll().observe(this,Observer{
                if(it.errorRelay){
                    binding.relayAllOff.isEnabled = false
                    showToast(errorRelayStr)
                }else{
                    switchingAllSwitch(false)
                }
            })
        }
        setContentView(binding.root)
    }

    private fun initView(){
        errorRelayStr = getString(R.string.relay_error)
        errorSensorStr = getString(R.string.data_from_sensor)
        dataFromRepository = ViewModelProvider(this).get(PoolInfoViewModel::class.java)
        dataFromRelay = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        dataInitialRelay = ViewModelProvider(this).get(InitializationStateRelayViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    private  fun showToast(text:String){
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun switchingAllSwitch(state : Boolean){
        if(launchPermission){
            launchPermission = false
            binding.relayFirst.isChecked = state
            binding.relaySecond.isChecked = state
            binding.relayThird.isChecked = state
            binding.relayFourth.isChecked = state
            binding.relayFifth.isChecked = state
            binding.relaySixth.isChecked = state
            binding.relaySeventh.isChecked = state
            binding.relayEighth.isChecked = state
        }
        launchPermission = true
    }

    private fun closingSwitch(compoundButton: CompoundButton) {
        compoundButton.isEnabled = false
        showToast(errorRelayStr)
    }
}