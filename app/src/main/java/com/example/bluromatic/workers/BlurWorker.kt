package com.example.bluromatic.workers

import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.ListenableWorker
import com.example.bluromatic.R

private val Any.resources: Resources?
    get() {
        TODO("Not yet implemented")
    }
private const val TAG = "BlurWorker"

class BlurWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(

    ): Result {
            val applicationContext =
                makeStatusNotification(
                    applicationContext.resources.getString(R.string.blurring_image),
                    applicationContext
                )

            return try {
                val picture = BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.android_cupcake
                )

                val output = blurBitmap(picture, 1)

                val outputUri = writeBitmapToFile(applicationContext, output)

                makeStatusNotification(
                    "Output is $outputUri",
                    applicationContext
                )

                Result.success()
            } catch (throwable: Throwable) {
                Log.e(
                    TAG,
                    applicationContext.resources?.getString(R.string.error_applying_blur),
                    throwable
                )
                ListenableWorker.Result.failure()
            }
    }

    private fun makeStatusNotification(message: String, context: Unit) {
        TODO("Not yet implemented")
    }

}



