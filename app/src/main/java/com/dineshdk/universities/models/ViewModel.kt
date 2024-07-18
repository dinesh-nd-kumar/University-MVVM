package com.dineshdk.universities.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dineshdk.universities.repository.Repository

class ViewModel : ViewModel() {

    val repo = Repository()
    fun getData(param:String) {
            repo.loadData(param)
    }
    fun observeUnivLiveData() : LiveData<List<University>> {
        return repo.getData()
    }
}