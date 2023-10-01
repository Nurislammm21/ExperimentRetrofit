package com.example.retrofitlesson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitlesson.Retrofit.AuthRequest
import com.example.retrofitlesson.Retrofit.MainAPI
import com.example.retrofitlesson.adapter.ProductAdapter
import com.example.retrofitlesson.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
       private lateinit var adapter : ProductAdapter
        lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            adapter = ProductAdapter()
        binding.rcVIew.layoutManager = LinearLayoutManager(this)
            binding.rcVIew.adapter = adapter


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(MainAPI::class.java)
                binding.sv.setOnQueryTextListener(object : OnQueryTextListener{
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(text: String?): Boolean {
                        CoroutineScope(Dispatchers.IO).launch {
                            val connect = text?.let { mainApi.getProductsByName(it) }
                            runOnUiThread{
                                binding.apply {
                                    if (connect != null) {
                                        adapter.submitList(connect.products)
                                    }

                                }
                            }
                        }
                       return true
                    }

                })

        }


    }
