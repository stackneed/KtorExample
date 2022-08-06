package com.whatsscan.ktorexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.whatsscan.ktorexample.adapter.DataAdapter
import com.whatsscan.ktorexample.databinding.ActivityMainBinding
import com.whatsscan.ktorexample.models.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.Main).launch {
            binding.recyclerView.adapter= DataAdapter(returnValue());
            Log.e("TAG", "onCreate: " + returnValue())
        }
    }

    private val apiService by lazy {
        ApiService.create()
    }

    private suspend fun returnValue(): List<ResponseModel> {
        return apiService.getProducts()
    }

    /*  val products = produceState(
          initialValue = emptyList<ResponseModel>(),
          producer = {
              value = apiService.getProducts()
          }
      )*/
}