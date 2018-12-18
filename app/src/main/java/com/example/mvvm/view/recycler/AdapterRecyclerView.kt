package com.example.mvvm.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.AdapterStarWarsBinding
import com.example.mvvm.model.Person
import com.example.mvvm.view.ui.MainActivity
import com.example.mvvm.view.ui.PersonDialog

class AdapterRecyclerView : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>(), BaseAdapter {

    @Suppress("UNCHECKED_CAST")
    override fun setData(data: MutableList<*>) {
        addItens(data as MutableList<Person>)
    }

    private val people: MutableList<Person> = ArrayList()

    private fun addItens(list: MutableList<Person>) {
        people.clear()
        people.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(people[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                com.example.mvvm.R.layout.adapter_star_wars,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return people.size
    }

    inner class ViewHolder(val binding: AdapterStarWarsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val textView = binding.root.findViewById<TextView>(R.id.text_item_name)
        fun bind(item: Person) {
            binding.item = item

            textView.setOnClickListener {
                val dialog = PersonDialog().apply { arguments = Bundle().apply { putSerializable("itemPerson", item) } }
                dialog.show((binding.root.context as MainActivity).supportFragmentManager, "dialog")
            }

            binding.executePendingBindings()
        }
    }
}