package com.example.app19

import android.annotation.SuppressLint
import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import com.google.android.gms.location.LocationServices
import org.osmdroid.api.IGeoPoint
import org.osmdroid.tileprovider.tilesource.ITileSource
import org.osmdroid.tileprovider.tilesource.TileSourceFactory

class MainViewModel(private val application: Application) : ViewModel() {

    val centreLocation: LiveData<GeoPoint> get() = _centreLocation
    private val _zoom = MutableLiveData(17.0)
    val zoom: LiveData<Double> get() = _zoom
    private val _orientation = MutableLiveData(0f)
    val orientation: LiveData<Float> get() = _orientation
    private val _tile = MutableLiveData<ITileSource>(TileSourceFactory.DEFAULT_TILE_SOURCE)
    val tile: LiveData<ITileSource> get() = _tile
    private var startPoint: GeoPoint = LENIN
    private val _centreLocation: MutableLiveData<GeoPoint> = MutableLiveData(startPoint)
    private var _myLocation: MyLocationNewOverlay? = null
    val myLocation get() = _myLocation!!

    init {
        getLastLocation()
    }

    fun setZoom(zoom: Double) {
        _zoom.value = zoom
    }

    fun setCentreLocation(location: IGeoPoint) {
        _centreLocation.value = GeoPoint(location)
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(application)
        fusedLocationProvider.lastLocation.addOnSuccessListener { location ->
            _centreLocation.value = GeoPoint(location.latitude, location.longitude)
        }
    }

    fun setTile(tileSource: ITileSource?) {
        tileSource.let { _tile.value = it }
    }

    fun setOrientation(mapOrientation: Float) {
        _orientation.value = mapOrientation
    }

    fun setMarkers(mapView: MapView) {
        mapMarkerPoints.forEach { pair ->
            val marker = Marker(mapView)
            marker.apply {
                position = pair.value
                icon = ContextCompat.getDrawable(application, R.drawable.baseline_place_24)
                title = pair.key
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            }
            mapView.overlays.add(marker)
        }
        mapView.invalidate()
    }

    fun setOptionsForMap(mapView: MapView) {
        val compassOverlay = CompassOverlay(
            application,
            InternalCompassOrientationProvider(application),
            mapView
        )
        compassOverlay.enableCompass()
        _myLocation = MyLocationNewOverlay(GpsMyLocationProvider(application), mapView)
        myLocation.enableMyLocation()
        val rotationGestureOverlay = RotationGestureOverlay(mapView)
        rotationGestureOverlay.isEnabled
        mapView.setMultiTouchControls(true)
        mapView.overlays.apply {
            add(rotationGestureOverlay)
            add(myLocation)
            add(compassOverlay)
        }
    }

    companion object {
        val LENIN = GeoPoint(59.954052, 30.355422)

        const val MONUMENT_LENIN = "Памятник В.И. Ленину"
        const val MONUMENT_PETER_I = "Памятник Петру I"
        const val MONUMENT_DOSTOEVSKY = "Памятник Ф.М. Достоевскому"
        const val PETER_I_HOUSE = "Домик Петра I"
        const val HERMITAGE = "Государственный Эрмитаж"
        val mapMarkerPoints = mapOf<String, GeoPoint>(
            MONUMENT_LENIN to GeoPoint(59.954052, 30.355422),
            MONUMENT_PETER_I to GeoPoint(59.936416, 30.302204),
            MONUMENT_DOSTOEVSKY to GeoPoint(59.927469, 30.34757),
            PETER_I_HOUSE to GeoPoint(59.953187, 30.330904),
            HERMITAGE to GeoPoint(59.940482, 30.314449),
        )
    }
}