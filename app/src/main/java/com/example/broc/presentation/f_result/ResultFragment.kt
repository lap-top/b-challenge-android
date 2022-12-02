package com.example.broc.presentation.f_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.example.broc.R
import com.example.broc.databinding.FragmentResultBinding
import com.example.broc.presentation.f_home.HomeViewModel


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getEmail()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btnMain.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToHomeFragment()
            findNavController().navigate(action)
        }
        return binding.root

    }


}