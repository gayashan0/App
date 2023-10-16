package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
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

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private  val newFeedFragment = NewFeedFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)


        button.setOnClickListener{
            loadHome()
        }

        button2.setOnClickListener{
            loadNewFeed()
        }
    }

    private fun loadHome(){
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if(fragment == null){
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,
                homeFragment).commit()
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                homeFragment).commit()
        }
    }

    private fun loadNewFeed(){
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if(fragment == null){
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,
                newFeedFragment).commit()
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                newFeedFragment).commit()
        }
    }


}