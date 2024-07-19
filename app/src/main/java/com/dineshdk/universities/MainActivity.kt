package com.dineshdk.universities

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dineshdk.universities.adapter.UniversityAdapter
import com.dineshdk.universities.databinding.ActivityMainBinding
import com.dineshdk.universities.models.ViewModel
import com.dineshdk.universities.others.Constant.DEFAULT_COUNTRY

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var universityAdapter: UniversityAdapter
    private lateinit var mViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecycler()

        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        binding.progressBar.visibility = View.VISIBLE
        mViewModel.getData(DEFAULT_COUNTRY)
            mViewModel.observeUnivLiveData().observe(this){
            universityAdapter.universityList =it
            universityAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
                binding.serchview.isActivated = false

        }
         binding.serchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                 query?.let {
                     binding.progressBar.visibility = View.VISIBLE
                     mViewModel.getData(it)

                 }
                 return true
             }

             override fun onQueryTextChange(newText: String?): Boolean {
                 newText?.let {
                 }
                 return true
             }
         })

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