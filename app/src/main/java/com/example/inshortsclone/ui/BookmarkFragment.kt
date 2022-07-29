package com.example.inshortsclone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.inshortsclone.NewsApplication
import com.example.inshortsclone.R
import com.example.inshortsclone.databinding.FragmentBookmarkBinding
import com.example.inshortsclone.model.Data
import com.example.inshortsclone.room.NewsDatabase
import com.example.inshortsclone.viewmodel.NewsDataViewModel
import com.example.inshortsclone.viewmodel.NewsDataViewModelFactory

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    lateinit var newsDataViewModel: NewsDataViewModel

    var index = 0

    val list: ArrayList<Data> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = (requireActivity().application as NewsApplication).newsDataRepository

        newsDataViewModel = ViewModelProvider(
            this,
            NewsDataViewModelFactory(repository)
        )[NewsDataViewModel::class.java]

        binding.btnNext.setOnClickListener {

            if (index < list.size - 1) {
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

        newsDataViewModel.getBookmarkNews().observe(viewLifecycleOwner) {
            list.clear()
            list.addAll(it)
            if (list.size > 0) {
                setData(loadNews())
                binding.tvNoBookmark.visibility = View.GONE
                binding.llNews.visibility = View.VISIBLE
            }
            else{
                binding.tvNoBookmark.visibility = View.VISIBLE
                binding.llNews.visibility = View.GONE
            }
        }

        binding.ivBookmark.setOnClickListener {
            newsDataViewModel.deleteBookmark(list[index])
            list.removeAt(index)
        }
    }

    private fun setData(data: Data) {
        binding.tvNewsData.text = data.content
        binding.tvNewsTitle.text = data.title
        Glide.with(requireActivity())
            .load(data.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivNewsImage)

    }

    private fun loadNews() = list[index]

    private fun nextNews() = list[++index]

    private fun previousNews() = list[--index]

}