package net.youapps.converter.backend.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.exoplayer2.ExoPlayer
import net.youapps.converter.ConverterApplication

class PlayerViewModel(appContext: Context) : ViewModel() {
    val player = ExoPlayer.Builder(appContext)
        .setUsePlatformDiagnostics(false)
        .build()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ConverterApplication) // ktlint-disable max-line-length
                PlayerViewModel(application)
            }
        }
    }
}
