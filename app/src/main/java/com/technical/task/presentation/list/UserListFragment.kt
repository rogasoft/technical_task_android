package com.technical.task.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.technical.task.databinding.FragmentUserListBinding
import com.technical.task.presentation.list.model.UserListViewState
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseBinding = null
    }

    private fun initView() {
        with(binding) {
            userListAddButton.setOnClickListener {
                userListViewModel.getUserList()
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            userListViewModel.stateFlow.collectLatest {
                when (it) {
                    is UserListViewState.LoadListSuccess -> { Log.d("User List", "State $it") }
                    UserListViewState.EmptyState -> { Log.d("User List", "State $it") }
                    UserListViewState.LoadingState -> { Log.d("User List", "State $it") }
                    UserListViewState.LoadListFailure -> { Log.d("User List", "State $it") }
                }
            }
        }
    }
}