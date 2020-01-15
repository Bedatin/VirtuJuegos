package com.example.virtujuegos.slider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.virtujuegos.MainActivity
import com.example.virtujuegos.R
import kotlinx.android.synthetic.main.activity_slider.*

class SliderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        init()
        btnSaltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        SizeFragment.newInstance()
                    }
                    1 -> {
                        SizeFragment.newInstance()
                    }
                    2 -> {
                        TemplateFragment.newInstance()
                    }
                    else -> {
                        TemplateFragment.newInstance()
                    }
                }
            }

            override fun getItemCount(): Int {
                return 4
            }
        }

        tabs.setViewPager(viewPager)

        (viewPager.adapter as FragmentStateAdapter).registerAdapterDataObserver(tabs.adapterDataObserver)

        /*TabLayoutMediator(tabs, viewPager) { tab, position ->
                 when(position) {
                    0 -> tab.setIcon(R.drawable.ic_launcher_foreground)
                    else -> tab.setIcon(R.drawable.ic_arrow_back_black_24dp)
                }
            }.attach()*/
    }
}

