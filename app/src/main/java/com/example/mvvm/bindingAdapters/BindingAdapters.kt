package com.example.mvvm.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.view.recycler.BaseAdapter
import com.example.mvvm.viewmodel.PeopleViewModel

class BindingAdapters {

    companion object {
        @BindingAdapter("setItems")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, items: MutableList<*>?) {
            recyclerView.adapter.let {adapter ->
                if (adapter is BaseAdapter) {
                    items?.let { adapter.setData(it) }
                }
            }
        }

        @BindingAdapter("getItems")
        @JvmStatic
        fun getItems(view: View, viewModel: Any) {
            if (viewModel is PeopleViewModel) {
                view.setOnClickListener {
                    viewModel.getPeople()
                }
            }
        }
    }


}