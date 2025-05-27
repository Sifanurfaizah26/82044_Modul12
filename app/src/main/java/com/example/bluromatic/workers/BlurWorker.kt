package com.example.bluromatic.workers

import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.ListenableWorker
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import com.example.bluromatic.R

private val Any.resources: Resources?
    get() {
        TODO("Not yet implemented")
    }
private const val TAG = "BlurWorker"

class BlurWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {

        // ADD THESE LINES
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)

        val applicationContext =
            makeStatusNotification(
                applicationContext.resources.getString(R.string.blurring_image),
                applicationContext
            )

        return@withContext try {
            // NEW code
            require(!resourceUri.isNullOrBlank()) {
                val errorMessage =
                    applicationContext.resources?.getString(R.string.invalid_input_uri)
                if (errorMessage != null) {
                    Log.e(TAG, errorMessage)
                }
                errorMessage!!

            }
        }
//     val picture = BitmapFactory.decodeResource(
//         applicationContext.resources,
//         R.drawable.android_cupcake
//     )

        val resolver = applicationContext.contentResolver

        val picture = BitmapFactory.decodeStream(
            resolver.openInputStream(Uri.parse(resourceUri))
        )
    }
}


