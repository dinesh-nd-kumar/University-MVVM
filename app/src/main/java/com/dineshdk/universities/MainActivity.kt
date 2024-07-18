package com.dineshdk.universities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dineshdk.universities.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var universityAdapter: UniversityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecycler()


        lifecycleScope.launch {
            binding.progressBar.isVisible = true
            val responce = Retrofit.api.getUniversityList()
            universityAdapter.universityList = responce.body()!!
            universityAdapter.notifyDataSetChanged()
            binding.progressBar.isVisible = false
        }



    }

    private fun setRecycler(){
        binding.rvUniversities.apply {
            universityAdapter = UniversityAdapter(this@MainActivity,null)
            adapter = universityAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

            val decorator = DividerItemDecoration(this@MainActivity,
                (layoutManager as LinearLayoutManager).orientation)
            addItemDecoration(decorator)

        }
    }
}