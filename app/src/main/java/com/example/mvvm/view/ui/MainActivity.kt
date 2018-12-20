package com.example.mvvm.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.viewmodel.PeopleViewModel
import org.jetbrains.anko.indeterminateProgressDialog

class MainActivity : AppCompatActivity() {

    private val progressLoading by lazy { indeterminateProgressDialog("Carregando", "").apply { setCancelable(false) } }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewModel = ViewModelProviders.of(this@MainActivity).get(PeopleViewModel::class.java)
            setLifecycleOwner(this@MainActivity)
            executePendingBindings()
        }

        binding.viewModel?.loading?.observe(this, Observer<Boolean> { boolean ->
            progressLoading.let { if (boolean) it.show() else it.hide() }
        })

        if (savedInstanceState == null) {
            binding.viewModel?.getPeople()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        progressLoading.dismiss()
    }
}