package com.technical.task.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.technical.task.common.DIALOG_REQUEST_KEY
import com.technical.task.common.DIALOG_RESULT_KEY
import com.technical.task.common.gone
import com.technical.task.common.visible
import com.technical.task.databinding.FragmentUserDialogBinding
import com.technical.task.presentation.dialog.model.UserDialogViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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
                initView()
                initObservers()
                setCancelable(true)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseBinding = null
    }

    private fun initView() {
        with(binding) {
            userDialogAddUserButton.setOnClickListener {
                userDialogViewModel.addUser(
                    name = userDialogNameEditText.text.toString(),
                    email = userDialogEmailEditText.text.toString())
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            userDialogViewModel.stateFlow.collectLatest {
                when (it) {
                    UserDialogViewState.EmptyState -> {  }
                    UserDialogViewState.LoadingState -> { loadLoadingState() }
                    UserDialogViewState.AddSuccess -> { loadSuccessState() }
                    UserDialogViewState.AddFailure -> { dismiss() }
                }
            }
        }
    }

    private fun loadSuccessState() {
        setFragmentResult(DIALOG_REQUEST_KEY, bundleOf(DIALOG_RESULT_KEY to true))
        dismiss()
    }

    private fun loadLoadingState() {
        with(binding) {
            userDialogProgressBar.visible()
            userDialogAddUserButton.gone()
        }
    }

    companion object {
        const val TAG = "UserDialogFragment"
    }
}