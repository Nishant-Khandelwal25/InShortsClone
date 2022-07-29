package com.example.inshortsclone.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inshortsclone.R
import com.example.inshortsclone.util.OnClickListener

class CategoriesAdapter(
    private val context: Context,
    private val list: List<String>
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    var clickPosition = 0

    var onClickListener: OnClickListener.OnClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category , parent , false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.tvCategoryName.text = list[position]
        if (clickPosition == position){
            holder.tvCategoryName.setBackgroundColor(context.resources.getColor(R.color.black))
            holder.tvCategoryName.setTextColor(context.resources.getColor(R.color.white))
        }
        else{
            holder.tvCategoryName.setBackgroundColor(context.resources.getColor(R.color.white))
            holder.tvCategoryName.setTextColor(context.resources.getColor(R.color.black))
        }
        holder.tvCategoryName.setOnClickListener{
            OnClickListener(position , onClickListener!! , "category").onClick(it)
            clickPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategoryName = view.findViewById<TextView>(R.id.tvCategory)
    }
}