package com.technical.task.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.technical.task.databinding.FragmentUserDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDialogFragment : DialogFragment() {

    private val userDialogViewModel: UserDialogViewModel by viewModels()

    private var baseBinding: FragmentUserDialogBinding? = null
    private val binding get() = baseBinding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        baseBinding = FragmentUserDialogBinding.inflate(layoutInflater)
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create().apply {
                setCancelable(true)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseBinding = null
    }
}