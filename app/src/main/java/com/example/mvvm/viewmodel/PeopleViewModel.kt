package com.example.mvvm.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.Person
import com.example.mvvm.remote.RetrofitBuilder
import com.example.mvvm.view.recycler.AdapterRecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class PeopleViewModel {

    //Deve ficar no construtor da classe
    private val repository = RetrofitBuilder().buildRetrofit()

    private val allPeople = ArrayList<Person>()
    val people = MutableLiveData<MutableList<Person>>()
    val loading = MutableLiveData<Boolean>()
    val erro = ObservableBoolean(false)
    val adapter by lazy { AdapterRecyclerView() }
    val hasNext = ObservableBoolean(false)
    private var page = 1

    fun getPeople() {
        erro.set(false)
        loading.value = true
        CoroutineScope(Dispatchers.Main).launch {
            val request = repository.getPerson(page)

            try {
                val result = request.await()

                if (result.next != null) {
                    hasNext.set(true)
                } else {
                    hasNext.set(false)
                }

                allPeople.addAll(result.results ?: emptyList())

                people.postValue(allPeople.toMutableList())
                page++
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                erro.set(true)
                people.postValue(emptyList<Person>().toMutableList())
                page = 1
            } finally {
                loading.value = false
            }
        }
    }
}