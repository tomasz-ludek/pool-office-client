package pl.ludek.smat.home.pool_office_client.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.ludek.smat.home.pool_office_client.R
import pl.ludek.smat.home.pool_office_client.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var errorRelayStr: String
    private lateinit var errorSensorStr: String
    private var ignoreSwitchCheckedChange: Boolean = true
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initModel()

        setContentView(binding.root)

        mainActivityViewModel.updatePoolInfo()
        mainActivityViewModel.updateCompleteRelayState()
    }

    private fun initModel() {
        mainActivityViewModel.poolInfoData.observe(this, Observer { poolInfoData ->
            if (poolInfoData == null) {
                binding.sensorDataView.text = errorSensorStr
            } else {
                binding.sensorDataView.text =
                    "T1= ${poolInfoData.t1} T2= ${poolInfoData.t2} T3= ${poolInfoData.t3} P1= ${poolInfoData.p1}"
            }
        })
        mainActivityViewModel.completeRelayStateData.observe(this, Observer { completeRelayData ->
            if (completeRelayData == null) {
                showToast(errorRelayStr)
            } else {
                ignoreSwitchCheckedChange = true
                completeRelayData.forEachIndexed { index, state ->
                    if (index < binding.switchPane.size) {
                        val switch = binding.switchPane[index] as SwitchCompat
                        switch.isChecked = state
                    }
                }
            }
            ignoreSwitchCheckedChange = false
        })
        mainActivityViewModel.singleRelayStateData.observe(this, Observer { relayData ->
            if (relayData.errorRelay) {
                showToast(errorRelayStr)
            }
            ignoreSwitchCheckedChange = true
            if (relayData.relayNumber == RELAY_ID_ALL) {
                binding.switchPane.forEach { switch ->
                    (switch as SwitchCompat).isChecked = relayData.stateRelay
                }
            } else {
                val switch = binding.switchPane[relayData.relayNumber] as SwitchCompat
                switch.isChecked = relayData.stateRelay
            }
            ignoreSwitchCheckedChange = false
        })
    }

    private fun initView() {
        errorRelayStr = getString(R.string.relay_error)
        errorSensorStr = getString(R.string.data_from_sensor)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.switchPane.forEachIndexed { index, view ->
            val switch = view as SwitchCompat
            switch.setOnCheckedChangeListener { _, checked ->
                if (!ignoreSwitchCheckedChange) {
                    mainActivityViewModel.switchRelay(index, checked)
                }
            }
        }
        binding.relayAllOn.setOnClickListener {
            mainActivityViewModel.switchRelay(RELAY_ID_ALL, true)
        }
        binding.relayAllOff.setOnClickListener {
            mainActivityViewModel.switchRelay(RELAY_ID_ALL, false)
        }
    }

    private fun showToast(text: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}