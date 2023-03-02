package com.example.hashtaglist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hashtaglist.databinding.ItemHashtagBinding

class HashtagAdapter(private val onCLick : (tag : String) -> Unit):
    RecyclerView.Adapter<HashtagAdapter.HashtagViewHolder>() {

    private val data = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashtagViewHolder {
        return HashtagViewHolder(
            ItemHashtagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HashtagViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: ArrayList<String>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class HashtagViewHolder(private val binding: ItemHashtagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hashtag: String) {
            binding.hashtagTextView.text = hashtag

            itemView.setOnClickListener {
                onCLick(hashtag)
            }
        }
    }
}
