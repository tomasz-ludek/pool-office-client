package pl.ludek.smat.home.pool_office_client.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.ludek.smat.home.pool_office_client.R
import pl.ludek.smat.home.pool_office_client.data.apiservice.PoolInfoData

class CustomAdapter (poolInfoData: PoolInfoData, stringArray: Array<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    private lateinit var dataArray: Array<String>
   init {
       if(poolInfoData.errorRelay){
           dataArray = arrayOf(stringArray[2])
       }else{
           dataArray = arrayOf(stringArray[0] +  poolInfoData.t1.toString(),
               stringArray[0] + poolInfoData.t2.toString(),
               stringArray[0] + poolInfoData.t3.toString(),
               stringArray[1] + poolInfoData.p1.toString())
       }
   }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pool_info_text_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataArray[position]
    }

    override fun getItemCount() = dataArray.size
}