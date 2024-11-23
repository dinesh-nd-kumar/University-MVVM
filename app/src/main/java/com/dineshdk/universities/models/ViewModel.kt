package com.dineshdk.universities.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dineshdk.universities.repository.Repository

class ViewModel : ViewModel() {

    private val repo = Repository()
    fun loadData(param:String) {
            repo.loadData(param)
    }
    fun getUnivLiveData() : LiveData<List<University>> {
        return repo.getData()
    }
}