package com.technical.task.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.technical.task.R
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
                    UserDialogViewState.NameValidationFailure -> { loadNameValidationFailureState() }
                    UserDialogViewState.EmailValidationFailure -> { loadEmailValidationFailureState() }
                    UserDialogViewState.AddSuccess -> { loadSuccessState() }
                    UserDialogViewState.AddFailure -> { loadGlobalFailureState() }
                    UserDialogViewState.NetworkFailure -> { loadGlobalFailureState() }
                }
            }
        }
    }

    private fun loadSuccessState() {
        setDialogResult(true)
    }

    private fun loadLoadingState() {
        with(binding) {
            userDialogProgressBar.visible()
            userDialogAddUserButton.gone()
        }
    }

    private fun loadNameValidationFailureState() {
        binding.userDialogNameEditText.error = getString(R.string.dialog_name_validation)
    }

    private fun loadEmailValidationFailureState() {
        binding.userDialogEmailEditText.error = getString(R.string.dialog_email_validation)
    }

    private fun loadGlobalFailureState() {
        setDialogResult(false)
    }

    private fun setDialogResult(result: Boolean) {
        setFragmentResult(DIALOG_REQUEST_KEY, bundleOf(DIALOG_RESULT_KEY to result))
        dismiss()
    }

    companion object {
        const val TAG = "UserDialogFragment"
    }
}