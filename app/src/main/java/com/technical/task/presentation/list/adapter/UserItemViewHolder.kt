package com.technical.task.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.technical.task.databinding.ViewUserItemBinding
import com.technical.task.presentation.list.model.UserModel

class UserItemViewHolder(private val binding: ViewUserItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(userModel: UserModel, longClick: (UserModel) -> Unit) {
        with(binding) {
            userItemName.text = userModel.name
            userItemEmail.text = userModel.email
            binding.root.setOnLongClickListener {
                longClick(userModel)
                true }
        }
    }
}