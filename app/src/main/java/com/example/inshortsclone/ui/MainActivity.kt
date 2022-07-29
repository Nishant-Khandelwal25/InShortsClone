package com.example.inshortsclone.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.inshortsclone.R
import com.example.inshortsclone.databinding.ActivityMainBinding
import com.example.inshortsclone.model.Data
import com.example.inshortsclone.viewmodel.NewsDataViewModel

class MainActivity : AppCompatActivity() {
    lateinit var newsDataViewModel: NewsDataViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val fragment1 = NewsFragment()
        val fragment2 = BookmarkFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment1)
            commit()
        }

        binding.tvBookmark.setOnClickListener {
            binding.ivBack.visibility = View.VISIBLE
            binding.tvBookmark.visibility = View.GONE
            binding.tvTitle.text = "Bookmarks"
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, fragment2)
                commit()
            }
        }

        binding.ivBack.setOnClickListener {
            binding.ivBack.visibility = View.GONE
            binding.tvBookmark.visibility = View.VISIBLE
            binding.tvTitle.text = "InShortsClone"
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, fragment1)
                commit()
            }
        }
    }
}