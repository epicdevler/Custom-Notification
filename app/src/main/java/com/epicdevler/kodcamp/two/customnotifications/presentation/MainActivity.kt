package com.epicdevler.kodcamp.two.customnotifications.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.epicdevler.kodcamp.two.customnotifications.databinding.ActivityMainBinding
import com.epicdevler.kodcamp.two.customnotifications.utils.NotifierViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: NotifierViewModel by lazy { ViewModelProvider(this)[NotifierViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.click.setOnClickListener {
            viewModel.getData()
            binding.lottieView.playAnimation()
        }


    }

}