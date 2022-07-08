package com.github.boybeak.app_legacy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.github.boybeak.app_legacy.databinding.ActivityMainBinding
import com.tencent.tencentmap.mapsdk.maps.model.Circle
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            val latLng = LatLng(39.984059, 116.307771)
            binding.mainMap.map.addCircle(
                CircleOptions().center(latLng).radius(1000.0).fillColor(-0x66ffff01)
                    .strokeColor(-0xcc1200).strokeWidth(5F).clickable(true)
            )
            val opt = PolygonOptions()
            binding.mainMap.map.setOnMarkerClickListener {
                Toast.makeText(this, "MarkerClick", Toast.LENGTH_SHORT).show()
                true
            }
        }

    }

    override fun onStart() {
        super.onStart()
        binding.mainMap.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mainMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mainMap.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mainMap.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mainMap.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}