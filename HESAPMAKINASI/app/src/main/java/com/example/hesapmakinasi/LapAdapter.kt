package com.example.hesapmakinasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hesapmakinasi.databinding.RecyclerRowBinding

class LapAdapter(private val lapItems: ArrayList<LapModel>) : RecyclerView.Adapter<LapAdapter.ButtonLapVH>() {
    private lateinit var binding: RecyclerRowBinding

    class ButtonLapVH(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonLapVH {
        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerRowBinding.inflate(inflater, parent, false)
        return ButtonLapVH(binding)
    }
    override fun getItemCount(): Int {
        return lapItems.size
    }
    override fun onBindViewHolder(holder: ButtonLapVH, position: Int) {
        val item = lapItems[position]
        holder.binding.textViewLap.text = CommonUtils.formatKronometre(item.seconds)

        holder.binding.buttonDelete.setOnClickListener {
            val selectedItem = holder.absoluteAdapterPosition
            // absoluteAdapterPosition -> RecyclerView içindeki bir öğenin mutlak pozisyonunu (absolute position) döndürmek için kullanılır.
            removeItem(selectedItem)
        }
    }
    private fun removeItem(position: Int) {
        lapItems.removeAt(position)
        notifyItemRemoved(position)
    }
}
