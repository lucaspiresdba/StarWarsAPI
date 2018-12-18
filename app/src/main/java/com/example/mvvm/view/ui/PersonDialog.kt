package com.example.mvvm.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.mvvm.R
import com.example.mvvm.databinding.DialogPersonInfoBinding
import com.example.mvvm.model.Person

class PersonDialog : DialogFragment() {

    private lateinit var binding: DialogPersonInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_person_info, null, false)
        binding.person = arguments?.getSerializable("itemPerson") as Person
        binding.executePendingBindings()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

}