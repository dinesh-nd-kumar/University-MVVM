package com.dineshdk.universities

import android.content.Intent
import android.net.Uri
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

class MainActivity : AppCompatActivity(),UniversityAdapter.ItemClickListener {

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
        mViewModel.loadData(DEFAULT_COUNTRY)
            mViewModel.getUnivLiveData().observe(this){
            universityAdapter.universityList =it
            universityAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
                binding.serchview.isActivated = false

        }
         binding.serchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                 query?.let {
                     binding.progressBar.visibility = View.VISIBLE
                     mViewModel.loadData(it)

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
    override fun onItemClick(position:Int){
        var url = mViewModel.getUnivLiveData().value?.get(position)?.webPages?.get(0)
        if (!url?.startsWith("http://")!! && !url?.startsWith("https://")!!) {
            url = "http://$url"
        }
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)

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