package pl.ludek.smat.home.pool_office_client.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.ludek.smat.home.pool_office_client.data.apiservice.PoolInfoData
import pl.ludek.smat.home.pool_office_client.databinding.PoolInfoTextViewBinding

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

    class ViewHolder(viewBinding: PoolInfoTextViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
            val textView:TextView = viewBinding.textView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PoolInfoTextViewBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataArray[position]
    }

    override fun getItemCount() = dataArray.size
}