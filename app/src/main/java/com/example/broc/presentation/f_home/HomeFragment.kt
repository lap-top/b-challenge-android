package com.example.broc.presentation.f_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.broc.R
import com.example.broc.databinding.FragmentHomeBinding
import com.example.broc.presentation.f_dialog.InviteCancelDialogFragment
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // When cancel dialog is finished deleting email
        lifecycleScope.launch {
            viewModel.responseEvents.collect { event ->
                if (event is HomeViewModel.ResponseEvent.Success) {
                    findNavController().navigate(R.id.action_homeFragment_to_resultFragment)
                }
            }
        }
        viewModel.getEmail()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnMain.setOnClickListener {
            if (viewModel.state.value.email.isEmpty()) {
                findNavController().navigate(R.id.action_homeFragment_to_fragmentInvite)
            } else {
                displayCancelDialog()
            }
        }
    }

    private fun displayCancelDialog() {
        InviteCancelDialogFragment().show(childFragmentManager, "cancelDialog")
    }

}