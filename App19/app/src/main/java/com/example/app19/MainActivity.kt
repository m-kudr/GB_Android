package com.example.app19

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import com.example.app19.databinding.ActivityMainBinding
import android.preference.PreferenceManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map.values.isNotEmpty() && map.values.all { false }) {
            startLocation()
        } else {
            finish()
        }
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        checkPermission()
        viewModelFactory = ViewModelFactory(application)
        viewModel.setMarkers(binding.map)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.setOptionsForMap(binding.map)
        binding.button.setOnClickListener {
            val myLocation: GeoPoint? = viewModel.myLocation.myLocation
            myLocation?.let {
                binding.map.controller.animateTo(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeViewModel(binding, viewModel)
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.apply {
            setTile(binding.map.tileProvider.tileSource)
            setOrientation(binding.map.mapOrientation)
            setCentreLocation(binding.map.mapCenter)
            setZoom(binding.map.zoomLevelDouble)
        }
        viewModel.myLocation.onPause()
        binding.map.onPause()
    }

    private fun observeViewModel(binding: ActivityMainBinding, viewModel: MainViewModel) {
        viewModel.orientation.observe(this) {
            binding.map.mapOrientation = it
        }
        viewModel.zoom.observe(this) {
            binding.map.controller.setZoom(it)
        }
        viewModel.centreLocation.observe(this) {
            binding.map.setExpectedCenter(it)
        }
        viewModel.tile.observe(this) {
            binding.map.setTileSource(it)
        }
    }

    private fun startLocation() {
        Configuration.getInstance().load(
            this,
            PreferenceManager.getDefaultSharedPreferences(this)
        )
    }

    private fun checkPermission() {
        if (REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            startLocation()
        } else {
            launcher.launch(REQUIRED_PERMISSIONS)
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}