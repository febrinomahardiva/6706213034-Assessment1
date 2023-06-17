package org.d3if3034.assessment1_3034.network

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.d3if3034.assessment1_3034.MainActivity
import org.d3if3034.assessment1_3034.R

class UpdateWorker(
    context: Context, workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private val notificationId = 44

    override fun doWork(): Result {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
            Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(applicationContext,
            0, intent, 0)

        val builder = NotificationCompat.Builder(applicationContext,
                MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_local_gas_station_24)
            .setContentTitle(applicationContext.getString(
                R.string.notif_title))
            .setContentText(applicationContext.getString(
                R.string.notif_text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val manager = NotificationManagerCompat.from(applicationContext)
        manager.notify(notificationId, builder.build())

        return Result.success()
    }
}