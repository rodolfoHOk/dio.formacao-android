package me.dio.android.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import me.dio.android.workmanager.data.datasource.VideosDatasource
import me.dio.android.workmanager.ui.extensions.showBigPictureNotification
import java.util.concurrent.TimeUnit

class NotificationWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        Log.i(TAG, "doWork")

        val video = VideosDatasource.getRandomVideo()

        context.showBigPictureNotification(video)

        return Result.success()
    }

    companion object {
        private const val TAG = "NotificationWorker"
        private const val WORKER_NAME = "work_manager_app_worker_name"

        fun start(context: Context) {
            Log.i(TAG, "starting the worker")
            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    WORKER_NAME,
                    ExistingWorkPolicy.KEEP,
                    createRequest()
                )
        }

        fun startPeriodic(context: Context) {
            Log.i(TAG, "starting the worker periodic")
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    WORKER_NAME,
                    ExistingPeriodicWorkPolicy.KEEP,
                    createPeriodicRequest()
                )
        }

        private fun createRequest() = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(2, TimeUnit.MINUTES)
            .build()

        private fun createPeriodicRequest() =
            PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.MINUTES)
                .build()
    }

}
