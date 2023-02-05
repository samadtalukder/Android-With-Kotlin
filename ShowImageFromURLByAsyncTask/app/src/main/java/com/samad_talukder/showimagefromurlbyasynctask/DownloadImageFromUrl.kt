package com.samad_talukder.showimagefromurlbyasynctask

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

private lateinit var progressDialog: ProgressDialog

class DownloadImageFromUrl(
    private val imageView: AppCompatImageView,
    private val context: Context
) :
    AsyncTask<String, Void, Bitmap>() {

    override fun onPreExecute() {
        super.onPreExecute()
        showProgressDialog()
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    override fun doInBackground(vararg urls: String): Bitmap? {

        return try {
            val url = urls[0]
            val httpConnection = URL(url).openConnection() as HttpURLConnection
            httpConnection.doInput = true
            httpConnection.connect()

            if (httpConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val input = httpConnection.inputStream as InputStream
                BitmapFactory.decodeStream(input)?.also {
                    input.close()
                }

            } else {
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show()
                }
                /*Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show()
                }*/

                null
            }


        } catch (io: IOException) {
            Log.e("ERROR:", "${io.message}")
            null
        } catch (mal: MalformedURLException) {
            Log.e("ERROR:", "${mal.message}")
            null
        }
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        hideProgressDialog()
        result.let {
            imageView.setImageBitmap(it)
        }
    }

    private fun hideProgressDialog() {
        if (::progressDialog.isInitialized && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

}