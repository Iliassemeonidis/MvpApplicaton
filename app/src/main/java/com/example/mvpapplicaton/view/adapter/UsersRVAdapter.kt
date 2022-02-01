package com.example.mvpapplicaton.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpapplicaton.databinding.ItemViewBinding

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemViewBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos: Int = -1

        override fun setLogin(login: String) = with(vb) {
            tvLogin.text = login
        }
    }
}