package net.youapps.converter

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import net.youapps.converter.ui.theme.ConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConverterTheme {
                val navHostController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(navHostController = navHostController)
                }
            }
            LaunchedEffect(Unit) {
                requestNotificationPermissions()
            }
        }
    }

    private fun requestNotificationPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }
}
