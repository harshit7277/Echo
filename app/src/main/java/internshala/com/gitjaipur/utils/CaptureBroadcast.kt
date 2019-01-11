package internshala.com.gitjaipur.utils

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.telephony.TelephonyManager
import internshala.com.gitjaipur.activties.MainActivity
import internshala.com.gitjaipur.activties.MainActivity.Staticated.notificationManager
import internshala.com.gitjaipur.fragments.SongPlayingFragment


/**
 * Created by Harshit on 22/07/2018.
 */
internal class CaptureBroadcast : BroadcastReceiver() {

    object Statified {
        var incomingFlag = false

    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_NEW_OUTGOING_CALL) {

            CaptureBroadcast.Statified.incomingFlag = false
            try {
                MainActivity.Staticated.notificationManager?.cancel(1978)

                if (SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean) {
                    (SongPlayingFragment.Statified.mediaPlayer as MediaPlayer).pause()

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else {
            val tm: TelephonyManager = context.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager

            when (tm.callState) {

                TelephonyManager.CALL_STATE_RINGING -> {
                    CaptureBroadcast.Statified.incomingFlag = true

                    try {
                        if (SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean) {
                            (SongPlayingFragment.Statified.mediaPlayer as MediaPlayer).pause()

                            notificationManager?.cancel(1978)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                else -> {
                }
            }

        }

    }


}