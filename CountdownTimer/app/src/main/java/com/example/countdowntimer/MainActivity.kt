package com.example.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.SeekBar
import com.example.countdowntimer.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var runned: Boolean = false
    //private val circular_pro: ProgressBar
    //private var progressStatus: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //Log.d(TAG, "OnCreate")
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun updateState(state: Boolean) {
            if (!state) {
                binding.seekBar.isEnabled = true
                binding.btnStartStop.text = "START"
                //binding.progressBar.setProgress((binding.seekBar.progress * 20), false)
            } else {
                binding.seekBar.isEnabled = false
                binding.btnStartStop.text = "STOP"
                //binding.progressBar.setProgress((binding.seekBar.progress * 20), true)
            }
        }

        updateState(runned)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textViewCount.text = (binding.seekBar.progress * 20).toString()
                binding.btnStartStop.isEnabled = binding.seekBar.progress != 0
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        binding.btnStartStop.setOnClickListener {
            binding.progressBar.progress = binding.seekBar.progress * 20
            if (runned)
                runned = false
            else
                runned = true
            updateState(runned)
        }

    }
}