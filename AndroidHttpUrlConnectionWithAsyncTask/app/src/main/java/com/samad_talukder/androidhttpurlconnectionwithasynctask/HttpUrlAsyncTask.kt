package com.samad_talukder.androidhttpurlconnectionwithasynctask

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@SuppressLint("StaticFieldLeak")
class HttpUrlAsyncTask(private var activity: MainActivity?) :
    AsyncTask<String, String, String>() {

    override fun onPreExecute() {
        super.onPreExecute()

        activity?.MyprogressBar?.visibility = View.VISIBLE


    }

    override fun doInBackground(vararg params: String?): String {
        var result = ""
        var httpUrlConnection: HttpURLConnection? = null
        try {
            val loadUrl = URL(params[0])
            httpUrlConnection = loadUrl.openConnection() as HttpURLConnection
            httpUrlConnection.readTimeout = 8000
            httpUrlConnection.connectTimeout = 8000
            httpUrlConnection.doOutput = true
            httpUrlConnection.requestMethod="GET"
            httpUrlConnection.connect()

            val responseCode = httpUrlConnection.responseCode
            Log.e("HTTPAsync", "$responseCode")

            val inStream: InputStream = httpUrlConnection.inputStream
            val isReader = InputStreamReader(inStream)
            val bReader = BufferedReader(isReader)
            var tempStr: String?

            try {

                while (true) {
                    tempStr = bReader.readLine()
                    if (tempStr == null) {
                        break
                    }
                    result += tempStr
                }
            } catch (Ex: Exception) {
                Log.e("HTTPAsync", "Error in convertToString " + Ex.printStackTrace())
            }
        } catch (ex: Exception) {
            Log.e("HTTPAsync", "${ex.message}")
        } finally {
            httpUrlConnection?.disconnect()
        }
        return result
    }




    @SuppressLint("SetTextI18n")
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        if (result == "") {

            activity?.my_text?.text = "Network Error"
        } else {
            activity?.MyprogressBar?.visibility = View.INVISIBLE

            val jsonObject = JSONObject(result)

            val country = jsonObject.get("country_name")
            val ip = jsonObject.get("ip")

            activity?.my_text?.text = "IP: $ip, $country"
        }
    }
}