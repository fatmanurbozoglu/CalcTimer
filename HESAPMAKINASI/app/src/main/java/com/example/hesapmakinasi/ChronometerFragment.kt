package com.example.hesapmakinasi

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hesapmakinasi.databinding.FragmentChronometerBinding

class ChronometerFragment : Fragment() {
    private lateinit var binding: FragmentChronometerBinding
    var seconds: Long = 0
    var running = true
    var runnable: Runnable = Runnable { }
    var handler = Handler(Looper.getMainLooper()) // handler içerisine Looper.getMainLooper() ekle

    private var lapItems = ArrayList<LapModel>()
    private lateinit var lapAdapter: LapAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
// onCreateView içerisinde sadece bindingleri set et
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChronometerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonReset.setOnClickListener { resetChronometer(it) }
        binding.buttonStart.setOnClickListener { startChronometer(it) }
        binding.buttonStop.setOnClickListener { stopChronometer(it) }
        binding.buttonLap.setOnClickListener { lapChronometer(it) }

        binding.buttonLap.visibility = View.GONE
        binding.buttonStop.visibility = View.GONE
        binding.buttonReset.visibility = View.GONE


        initLapRecyclerview()

    }

    fun initLapRecyclerview() {
        lapAdapter = LapAdapter(lapItems)
        binding.recyclerView.adapter = lapAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }
    // fonksiyon isimlendirmede camelCase kullan
    fun startChronometer(view: View) {
        runnable = Runnable {
            seconds += 10
            val timeDisplay = CommonUtils.formatKronometre(seconds)
            binding.textViewChoronometer.text = timeDisplay
            handler.postDelayed(runnable, 10)  // 10 milisaniyede bir calisacak
        }
        handler.post(runnable)

        running = true
        binding.buttonStart.visibility = View.GONE
        binding.buttonLap.visibility = View.VISIBLE
        binding.buttonStop.visibility = View.VISIBLE
        binding.buttonReset.visibility = View.VISIBLE

    }

    fun stopChronometer(view: View) {
        handler.removeCallbacks(runnable)
        running = false
        binding.buttonStop.visibility = View.GONE // buton gizlendi
        binding.buttonStart.visibility = View.VISIBLE // buton görünür halde
    }
    @SuppressLint("SetTextI18n")
    fun resetChronometer(view: View) {
        handler.removeCallbacks(runnable)
        seconds = 0
        binding.textViewChoronometer.text = "00:00:00:00"
        running = false

        binding.buttonLap.visibility = View.GONE
        binding.buttonStart.visibility = View.VISIBLE
        binding.buttonStop.visibility = View.GONE
        binding.buttonReset.visibility = View.GONE

        lapItems.clear()    // recyclerView içerisinde yazılanları siler
        lapAdapter.notifyDataSetChanged()   // recyclerView i güncellemek için kullanılır

    }
    fun lapChronometer(view: View) {
        // her bastıkca bastıgı sureyi recyclerview icerisine kaydetsin
        // lap butonuna bastıktan sonra recyclerView içerisinde eklenen laplardan hangisine basarsan o satırı silsin
        running = true
        lapItems.add(LapModel(seconds))
        lapAdapter.notifyDataSetChanged()   // recyclerView i güncellemek için kullanılır
    }
}