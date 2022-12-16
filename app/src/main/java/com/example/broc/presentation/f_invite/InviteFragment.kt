package com.example.broc.presentation.f_invite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.broc.R
import com.example.broc.databinding.FragmentInviteBinding
import com.example.broc.presentation.home_dialog.InviteEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InviteFragment : Fragment() {
    private lateinit var binding: FragmentInviteBinding
    private val viewModel: InviteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invite, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            viewModel.responseEvents.collect { event ->
                if (event is InviteViewModel.ResponseEvent.Success) {
                    findNavController().navigate(InviteFragmentDirections.actionFragmentInviteToResultFragment(viewModel.state.value.email.get().toString()))
                }

            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentInvite_to_homeFragment)
        }

        binding.btnMain.setOnClickListener {
            viewModel.onEvent(InviteEvent.Submit)
        }
        return binding.root
    }


}