package com.example.mvvm.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.Person
import com.example.mvvm.remote.RetrofitBuilder
import com.example.mvvm.view.recycler.AdapterRecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class PeopleViewModel : ViewModel() {

    /**
     * Instância do repositório deve ficar no construtor desta classe. Como não estou usado injeção de dependência preferir instancia-lo dentro da classe
     */
    private val repository = RetrofitBuilder.buildRetrofit()

    private val allPeople = ArrayList<Person>()//Adicionando os dados conforme paginação
    val people =
        MutableLiveData<MutableList<Person>>()//variavel para observação da recycler view para popular os itens
    val loading = MutableLiveData<Boolean>()//Mostra o dialog de carregando
    val erro = ObservableBoolean(false)//Caso ocorra um erro mostra uma view com a mensagem de erro
    val adapter by lazy { AdapterRecyclerView() }//Adapter do recycler view
    val hasNext = ObservableBoolean(false)//Verifica se existe mais itens na paginação
    private var page = 1 //pagina a ser chamada


    /**
     * Não fiz nenhum tratamento de erro pois quero deixar o código mais simples possível
     */
    fun getPeople() {
        erro.set(false)
        loading.value = true
        CoroutineScope(Dispatchers.Main).launch {
            val request = repository.getPerson(page)

            try {
                val result = request.await()
                hasNext.set(result.next != null)
                allPeople.addAll(result.results ?: emptyList())
                people.postValue(allPeople.toMutableList())
                page++
            } catch (e: java.lang.Exception) {
                erro.set(true)
                allPeople.clear()
                people.postValue(allPeople)
                page = 1
            } finally {
                loading.value = false
            }
        }
    }
}