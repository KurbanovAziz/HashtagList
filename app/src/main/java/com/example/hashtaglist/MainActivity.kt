package com.example.hashtaglist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.hashtaglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = HashtagAdapter(this::onClick)
    private var hashtagList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
        initAdapter()
    }

    private fun onClick(tag : String){
        val message = binding.editText.text.toString()
        val splitMessage = message.split(" ").toMutableList()
        val tagIndex = findIndex(splitMessage, message)
        splitMessage[tagIndex] = tag
        binding.editText.setText(splitMessage.joinToString(" "))
        binding.editText.setSelection(binding.editText.text.length)
    }

    private fun findTag(text: String): ArrayList<String> {
        val result = arrayListOf<String>()
        val message = text.split(" ")
        for (i in message) {
            if (i.startsWith("#")) {
                result.add(i)
            }
        }
        return result
    }

    private fun initListener() {
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString().trim()
                if (text.isNotEmpty() && text.contains("#")) {
                    binding.recyclerView.visibility = View.VISIBLE
                } else {
                    binding.recyclerView.visibility = View.GONE
                }
            }
        })

        binding.addButton.setOnClickListener {
            hashtagList.addAll(findTag(binding.editText.text.toString()))
            adapter.addData(hashtagList)
            binding.editText.text.clear()
        }
    }

    private fun initAdapter(){
        binding.recyclerView.adapter = adapter
    }

    private fun findIndex(splitMessage: MutableList<String>, message: String): Int {
        for (i in findTag(message)) {
            if (!hashtagList.contains(i))
                return splitMessage.indexOf(i)
        }
        return 0
    }
}
