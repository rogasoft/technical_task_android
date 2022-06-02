package com.technical.task.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.technical.task.presentation.list.model.UserModel

class UserItemDiffCallback : DiffUtil.ItemCallback<UserModel>() {

    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) = oldItem == newItem

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) = oldItem == newItem
}