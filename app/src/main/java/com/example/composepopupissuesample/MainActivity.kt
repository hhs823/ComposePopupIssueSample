package com.example.composepopupissuesample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.composepopupissuesample.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.viewpager) {
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount(): Int = 1
                override fun createFragment(position: Int): Fragment {
                    return MainFragment()
                }
            }
            isUserInputEnabled = false
        }
    }
}
