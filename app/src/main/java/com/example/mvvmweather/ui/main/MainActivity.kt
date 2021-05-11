package com.example.mvvmweather.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmweather.BR
import com.example.mvvmweather.R
import com.example.mvvmweather.data.models.ApiResponse
import com.example.mvvmweather.data.models.AreaResponse
import com.example.mvvmweather.data.models.Daily
import com.example.mvvmweather.databinding.ActivityMainBinding
import com.example.mvvmweather.ui.ActivityBindingProvider
import com.example.mvvmweather.ui.BaseNavigator
import com.example.mvvmweather.ui.NotifyType
import com.example.mvvmweather.ui.ViewModelProviderFactory
import com.example.mvvmweather.util.HAlert
import java.util.*


class MainActivity : AppCompatActivity(), BaseNavigator {

    private val permissionRequestId = 118
    private val permissionRequired = Manifest.permission.ACCESS_FINE_LOCATION
    private lateinit var viewModel: MainViewModel
    private val binding: ActivityMainBinding by ActivityBindingProvider(R.layout.activity_main)
    private lateinit var listAdapter: ExpandableListAdapter
    private val listArray: ArrayList<Daily> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviderFactory(MainViewModel(application, this, this@MainActivity))
            .create(MainViewModel::class.java)

        binding.setVariable(BR.viewModel, viewModel)

        listAdapter = ExpandableListAdapter(
            R.layout.list_item_forecast,
            BR.daily,
            listArray as AbstractList<Daily>
        )

        binding.recyclerViewWeather.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = listAdapter
            isNestedScrollingEnabled = false
        }

        binding.swipeRefreshLayout.setOnRefreshListener { getLocation() }

        binding.editTextCityName.setOnEditorActionListener(
            OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                    val cityName = binding.editTextCityName.text.toString().trim()
                    viewModel.getWeatherForCityName(cityName)
                    return@OnEditorActionListener true
                }

                false
            })

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        val granted = checkPermission(permissionRequired)
        if (granted) {
            getLocation()
        } else {
            requestPermissions(arrayOf(permissionRequired), permissionRequestId)
        }
    }

    private fun checkPermission(permission: String): Boolean {
        val check =
            ActivityCompat.checkSelfPermission(this, permission)
        if (check == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permissionRequestId -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    HAlert.showAlertDialog(this, "Hey", "App needs to access your location");
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val service = getSystemService(LOCATION_SERVICE) as LocationManager
        val enabled = service
            .isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!enabled) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        } else {
            viewModel.locationObserver.value = getBestLocation(service)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getBestLocation(mLocationManager: LocationManager): Location? {
        val providers: List<String> = mLocationManager.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            val l: Location = mLocationManager.getLastKnownLocation(provider) ?: continue
            if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                // Found best last known location: %s", l);
                bestLocation = l
            }
        }
        return bestLocation
    }

    override fun notifyUser(text: String, type: NotifyType) {
        when (type) {
            NotifyType.TYPE_TOAST -> HAlert.showToast(this, text)
            NotifyType.TYPE_ALERT -> HAlert.showAlertDialog(
                this,
                getString(R.string.app_name),
                text
            )
            NotifyType.TYPE_SNACKBAR -> HAlert.showSnackBar(
                this.findViewById(android.R.id.content),
                text
            )
        }
    }

    override fun onNavigateBack(className: String?) {
        // decide if you want to navigate user back
    }

    override fun onNavigateForward(className: String?) {
        // decide if you want to navigate user forward
    }

    override fun onWeatherResponse(obj: Any?) {

        binding.swipeRefreshLayout.isRefreshing = false

        if (obj != null && obj is ApiResponse) {

            try {
                listArray.clear()
                listArray.addAll(obj.daily)
                binding.current = obj.current
                listAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onAreaResponse(obj: Any?) {

        if (obj != null && obj is AreaResponse) {

            try {
                val toolbarTitle = String.format("%s - %s", obj[0].name, obj[0].country)
                binding.toolbar.title = toolbarTitle
            } catch (e: Exception) {
                binding.toolbar.title = getString(R.string.app_name)
                e.printStackTrace()
            }
        }
    }
}