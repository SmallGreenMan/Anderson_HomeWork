package android.examample.imageloader_hw_31.OkHttp


import android.examample.imageloader_hw_31.databinding.ActivityOkHttpBinding
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL







private const val ENDPOINT = "http://192.168.5.3:3000"
private const val BOOKS_URI = "/books"
private const val TITLE = "title"

class OkHttp : AppCompatActivity() {

    private lateinit var binding: ActivityOkHttpBinding
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOkHttpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBookButton.setOnClickListener {
            val book = binding.bookNameEditText.text
            Thread {
                addBook(book.toString())
            }.start()
        }
        binding.remuveBookButton.setOnClickListener {
            val bookId = binding.bookNameEditText.text
            Thread {
                removeBook(bookId.toString())
            }.start()
        }
        binding.refreshButton.setOnClickListener {
            binding.textViewBookList.text = ""
            Thread {
                getBookAndShowIt()
            }.start()
        }

        // --- OkHttp
        binding.refreshOkHttpButton.setOnClickListener {
            binding.textViewBookList.text = ""
            run(ENDPOINT + BOOKS_URI)
        }
        binding.addBookOkHttpButton.setOnClickListener {
            val book = binding.bookNameEditText.text
            Thread {
                post(ENDPOINT + BOOKS_URI, book.toString())
            }.start()
        }
        binding.remuveBookOkHttpButton.setOnClickListener{
            val bookId = binding.bookNameEditText.text
            Thread {
                delete(ENDPOINT + BOOKS_URI, bookId.toString())
            }.start()
        }
    }

    fun showDataToUi(text: String){
        val books = mutableListOf<String>()
        val json = JSONArray(text)
        for (i in 0 until json.length()){
            val jsonBook = json.getJSONObject(i)
            val title = jsonBook.getString(TITLE)
            val id = jsonBook.getString("id")
            books.add("$id : $title")
        }

        Handler(Looper.getMainLooper()).post{
            binding.textViewBookList.text = books.reduce{acc, s -> "$acc\n$s"}
        }
    }

    // --- HttpURLConnection
    @WorkerThread
    fun getBookAndShowIt(){
        val httpUrlonnection = URL(ENDPOINT + BOOKS_URI).openConnection() as HttpURLConnection
        httpUrlonnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "GET"
            doInput = true
        }
        if (httpUrlonnection.responseCode != HttpURLConnection.HTTP_OK){
            Toast.makeText(baseContext, "Connection error: $httpUrlonnection.responseCode", Toast.LENGTH_SHORT).show()
            return
        }
        val streamReader = InputStreamReader(httpUrlonnection.inputStream)
        var text: String = ""
        streamReader.use {
            text = it.readText()
        }
        httpUrlonnection.disconnect()
        runOnUiThread {
            showDataToUi(text)
        }
    }

    @WorkerThread
    fun removeBook(id: String){
        val httpUrlConnection = URL(ENDPOINT + BOOKS_URI + "/" + id).openConnection() as HttpURLConnection
        httpUrlConnection.apply {
            connectTimeout = 10000
            requestMethod = "DELETE"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        httpUrlConnection.responseCode
        httpUrlConnection.disconnect()
        getBookAndShowIt()
    }

    @WorkerThread
    fun addBook(bookName: String){
        val httpUrlConnection = URL(ENDPOINT + BOOKS_URI).openConnection() as HttpURLConnection
        val body = JSONObject().apply {
            put(TITLE,bookName)
        }
        httpUrlConnection.apply {
            connectTimeout = 10000
            requestMethod = "POST"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        OutputStreamWriter(httpUrlConnection.outputStream).use{
            it.write(body.toString())
        }
        httpUrlConnection.responseCode
        httpUrlConnection.disconnect()
        getBookAndShowIt()
    }

    // --- OkHttp
    private fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response){
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    showDataToUi(response.body!!.string())
                }
            }
        })
    }

    private fun post(url: String, bookName: String) {
         val postBody = JSONObject().apply {
            put(TITLE,bookName)
        }

        val request = Request.Builder()
            .url(url)
            .post(postBody.toString().toRequestBody("application/json; charset=utf-8".toMediaType()))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            //println(response.body!!.string())
        }
    }

    private fun delete(url: String, id: String) {
        val request = Request.Builder()
            .url("$url/$id")
            .delete()
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) //throw IOException("Unexpected code $response")
                runOnUiThread{
                    Toast.makeText(baseContext, "Connection error: ${response.code}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}