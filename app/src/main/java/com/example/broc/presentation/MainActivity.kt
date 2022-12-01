package com.example.broc.presentation

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.broc.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is MainViewModel.MainState.Error -> binding.btnTest.text = "abc"
                    is MainViewModel.MainState.Success -> binding.btnTest.text = state.data.toString()
                }

            }
        }
       // val navController = findNavController(R.id.nav_host_fragment_content_main)

    }

}