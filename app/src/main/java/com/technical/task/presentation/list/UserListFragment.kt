package com.technical.task.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.technical.task.common.DIALOG_REQUEST_KEY
import com.technical.task.common.DIALOG_RESULT_KEY
import com.technical.task.common.gone
import com.technical.task.common.showAlertDialog
import com.technical.task.common.showDeleteSuccessSnackbar
import com.technical.task.common.showEmptyListSnackbar
import com.technical.task.common.showGeneralFailureSnackbar
import com.technical.task.common.visible
import com.technical.task.databinding.FragmentUserListBinding
import com.technical.task.presentation.dialog.UserDialogFragment
import com.technical.task.presentation.dialog.UserDialogFragment.Companion.TAG
import com.technical.task.presentation.list.adapter.UserAdapter
import com.technical.task.presentation.list.model.UserListViewState
import com.technical.task.presentation.list.model.UserModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val userListViewModel: UserListViewModel by viewModels()

    private var baseBinding: FragmentUserListBinding? = null
    private val binding get() = baseBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseBinding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        userListViewModel.getUserList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseBinding = null
    }

    private fun initView() {
        binding.root.showGeneralFailureSnackbar()
        with(binding) {
            userListAddButton.setOnClickListener {
                UserDialogFragment().show(childFragmentManager, TAG)
            }
        }
        childFragmentManager.setFragmentResultListener(DIALOG_REQUEST_KEY, viewLifecycleOwner) { key, bundle ->
            bundle.getBoolean(DIALOG_RESULT_KEY).let {
                if (it) userListViewModel.getUserList() }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            userListViewModel.stateFlow.collectLatest {
                when (it) {
                    is UserListViewState.LoadListSuccess -> { loadSuccessState(it.list) }
                    UserListViewState.EmptyState -> { loadEmptyState() }
                    UserListViewState.LoadingState -> { loadLoadingState() }
                    UserListViewState.DeleteSuccess -> { loadDeleteSuccessState() }
                    UserListViewState.GeneralFailure -> { loadFailureState() }
                }
            }
        }
    }

    private fun loadEmptyState() {
        binding.root.showEmptyListSnackbar()
    }

    private fun loadSuccessState(list: List<UserModel>) {
        with(binding) {
            userListRecyclerView.adapter = UserAdapter {
                requireContext().showAlertDialog { userListViewModel.deleteUser(it) }
            }.apply { submitList(list) }
            userListProgressBar.gone()
        }
    }

    private fun loadLoadingState() {
        binding.userListProgressBar.visible()
    }

    private fun loadDeleteSuccessState() {
        binding.root.showDeleteSuccessSnackbar()
    }

    private fun loadFailureState() {
        binding.root.showGeneralFailureSnackbar()
    }
}