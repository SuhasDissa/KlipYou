package net.youapps.converter.backend.services

import android.content.Intent
import com.arthenica.ffmpegkit.FFmpegKit
import net.youapps.converter.backend.models.ConverterState
import net.youapps.converter.backend.models.FFMPEGCommand
import net.youapps.converter.backend.models.FFMPEGStatus
import net.youapps.converter.backend.util.FFMPEGUtil

class FFMPEGServiceImpl : FFMPEGService() {
    var onFFMPEGStatus: (FFMPEGStatus) -> Unit = {}

    override val notificationTitle: String
        get() = "Converting"

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        updateNotification()
        val extras = intent.extras
        extras?.let {
            val from = extras.getSerializable("command") as FFMPEGCommand?
            from?.let {
                runCatching {
                    converterState = ConverterState.ACTIVE
                    onConverterStateChanged(converterState)
                }
                FFMPEGUtil.processCommand(
                    this,
                    command = it,
                    onFinished = { sessionResult ->
                        onFFMPEGStatus(sessionResult)
                        stopSelf()
                    }
                ) { statistics ->
                    onFFMPEGStatus(FFMPEGStatus.Running(statistics))
                }
            }
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        FFmpegKit.cancel()
        super.onDestroy()
    }
}
