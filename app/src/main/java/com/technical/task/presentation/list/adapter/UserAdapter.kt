package com.technical.task.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.technical.task.databinding.ViewUserItemBinding
import com.technical.task.presentation.list.model.UserModel

class UserAdapter(private val longClick: (UserModel) -> Unit) :
    ListAdapter<UserModel, UserItemViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder =
        UserItemViewHolder(ViewUserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(getItem(position)) {
            longClick(it)
        }
    }
}