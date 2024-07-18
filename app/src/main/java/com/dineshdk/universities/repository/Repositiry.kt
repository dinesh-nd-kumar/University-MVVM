package com.dineshdk.universities.repository

import androidx.lifecycle.MutableLiveData
import com.dineshdk.universities.models.University
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository {
    private var universityLiveData = MutableLiveData<List<University>>()

    fun loadData(param:String) {
        GlobalScope.launch {
            universityLiveData.postValue( Retrofit.api.getUniversityList(param).body()!!)
        }
    }
    fun getData(): MutableLiveData<List<University>> {
        return universityLiveData
    }

}