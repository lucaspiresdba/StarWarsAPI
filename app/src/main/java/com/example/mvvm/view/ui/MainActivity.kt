package com.example.mvvm.view.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.viewmodel.PeopleViewModel
import org.jetbrains.anko.indeterminateProgressDialog

class MainActivity : AppCompatActivity() {

    private val vm = PeopleViewModel()
    private val progressLoading by lazy { indeterminateProgressDialog("Carregando", "").apply { setCancelable(false) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = (DataBindingUtil.setContentView(this, R.layout.activity_main))
        binding.viewModel = vm
        binding.setLifecycleOwner(this)
        binding.executePendingBindings()

        binding.viewModel!!.getPeople()

        vm.loading.observe(this, Observer<Boolean> { boolean ->
            progressLoading.let { if (boolean) it.show() else it.hide() }
        })
    }
}