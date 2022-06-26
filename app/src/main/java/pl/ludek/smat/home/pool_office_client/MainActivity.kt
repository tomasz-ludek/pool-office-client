package pl.ludek.smat.home.pool_office_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
     var common: PoolInfoService = Common.retrofitService
    private val dataFromSensor: SensorsViewModel by viewModels()
    private lateinit var sensorDataTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDataFromSensor()
      //  getDataFromSensorSecondVersion()
        sensorDataTextView = findViewById(R.id.sensorDataView)
        val dataFromSensorObserver = Observer<PoolInfoData>{inputDataFromSensor ->
            val rez:String =" T1= " + inputDataFromSensor.t1 +
                            " T2= " + inputDataFromSensor.t2 +
                            " T3= " + inputDataFromSensor.t3 +
                            " P1= " + inputDataFromSensor.p1
            sensorDataTextView.text = rez
        }
            dataFromSensor.currentSensorData.observe(this,dataFromSensorObserver)
    }

    private fun getDataFromSensor(){
        val data = PoolInfoInterface.create().getSensorData()
        data.enqueue(object : Callback<PoolInfoData>{
            override fun onResponse(
                call: Call<PoolInfoData>,
                response: Response<PoolInfoData>
            ) {
              if( response?.body() != null){
                    dataFromSensor.loadPoolInfoData(response.body()!!)
                  Log.d("LOG", response?.body().toString())
              }
            }
            override fun onFailure(call: Call<PoolInfoData>, t: Throwable) {
               Log.d("LOG", t.message.toString())
            }
        })
    }

    private fun getDataFromSensorSecondVersion(){
        common.getSensorData().enqueue(object : Callback<PoolInfoData>{
            override fun onResponse(
                call: Call<PoolInfoData>,
                response: Response<PoolInfoData>
            ) {
                val responseVar = response.body()
                dataFromSensor.loadPoolInfoData(response.body()!!)
                Log.d("LOG", responseVar.toString())
            }

            override fun onFailure(call: Call<PoolInfoData>, t: Throwable) {
                Log.d("LOG", t.message.toString())
            }
        })
    }
}