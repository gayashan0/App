package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.News
import com.example.myapplication.database.NewsDatabase
import com.example.myapplication.database.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewFeedFragment : Fragment() {
    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel: MainActivityData

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_feed, container, false)


        val recyclerView: RecyclerView = view.findViewById(R.id.rvNews)
        val repository = NewsRepository(NewsDatabase.getInstance(requireContext()))

        val addNews: Button = view.findViewById(R.id.btnAddNews)

       activity?.runOnUiThread {
           addNews.setOnClickListener {
               displayAlert(repository)
           }

           viewModel = ViewModelProvider(this)[MainActivityData::class.java]

           viewModel.data.observe(viewLifecycleOwner) {
               adapter = NewsAdapter(it, repository, viewModel)
               recyclerView.adapter = adapter
               recyclerView.layoutManager = LinearLayoutManager(context)
           }

           CoroutineScope(Dispatchers.IO).launch {
               val data = repository.getAllNews()
            requireActivity().runOnUiThread {
                viewModel.setData(data)
            }

           }
       }
        return view
    }


    private fun displayAlert(repository: NewsRepository){

        activity?.runOnUiThread {

            val builder = AlertDialog.Builder(requireContext())

            builder.setTitle(getText(R.string.alertTitle))
            builder.setMessage("Enter the News below :")

            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("Ok") { dialog, which ->
                val item = input.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    repository.insert(News(item))
                    val data = repository.getAllNews()
                    requireActivity().runOnUiThread {


                        viewModel.setData(data)
                    }
                }

            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}