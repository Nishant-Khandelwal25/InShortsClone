package com.example.inshortsclone.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inshortsclone.NewsApplication
import com.example.inshortsclone.R
import com.example.inshortsclone.adapter.CategoriesAdapter
import com.example.inshortsclone.databinding.FragmentNewsBinding
import com.example.inshortsclone.model.Data
import com.example.inshortsclone.util.OnClickListener
import com.example.inshortsclone.viewmodel.NewsDataViewModelFactory
import com.example.inshortsclone.viewmodel.NewsDataViewModel

class NewsFragment : Fragment() {
    lateinit var newsDataViewModel: NewsDataViewModel
    val list: ArrayList<Data> = ArrayList()
    var index = 0
    private lateinit var adapter: CategoriesAdapter

    private var mLastClickTime: Long = 0
    var category: String = "all"

    val categoryList: List<String> = arrayListOf(
        "all",
        "national",
        "business",
        "sports",
        "world",
        "politics",
        "technology",
        "startup",
        "entertainment",
        "miscellaneous",
        "science",
        "automobile"
    )

    lateinit var binding: FragmentNewsBinding
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = (requireActivity().application as NewsApplication).newsDataRepository

        newsDataViewModel = ViewModelProvider(
            this,
            NewsDataViewModelFactory(repository)
        )[NewsDataViewModel::class.java]

        setUpRecyclerView()

        dialog = ProgressDialog(context)
        dialog.setMessage("Loading News....")
        dialog.setCancelable(false)
        getNews()

        newsDataViewModel.newsData.observe(viewLifecycleOwner) {
            dialog.dismiss()
            Log.e("MainActivity", it.data.toString())
            list.clear()
            list.addAll(it.data)
            setData(loadNews())
        }

        binding.btnNext.setOnClickListener {

            if (index < list.size-1) {
                setData(nextNews())
            } else {
                Toast.makeText(context, "Last News", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (index > 0) {
                setData(previousNews())
            } else {
                Toast.makeText(context, "First News", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBookmark.setOnClickListener {
            newsDataViewModel.addBookmark(list[index])
        }
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvCategories.layoutManager = layoutManager
        adapter = CategoriesAdapter(requireContext(), categoryList)
        adapter.onClickListener = onClickCallback
        binding.rvCategories.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getNews() {
        dialog.show()
        newsDataViewModel.getNews(category)
    }

    private fun setData(data: Data) {
        binding.tvNewsTitle.text = data.title
        binding.tvNewsData.text = data.content
        Glide.with(this)
            .load(data.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivNewsImage)

    }

    private fun loadNews() = list[index]

    private fun nextNews() = list[++index]

    private fun previousNews() = list[--index]


    private val onClickCallback = object : OnClickListener.OnClickCallback {
        override fun onClicked(view: View, position: Int, type: String) {
            if (!isClicked()) {
                if (type == "category") {
                    category = categoryList[position]
                    getNews()
                }
            }
        }

    }

    private fun isClicked(): Boolean {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return true
        } else {
            mLastClickTime = SystemClock.elapsedRealtime()
        }
        return false
    }

}