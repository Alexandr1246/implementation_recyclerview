package com.example.train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.train.databinding.ActivityMainBinding
import com.example.train.model.Users
import com.example.train.model.UsersListener
import com.example.train.model.UsersService

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: UsersAdapter

    private val usersService: UsersService
    get() = (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter(object : UserActionListener{
            override fun onUserDetails(user: Users) {
                Toast.makeText(this@MainActivity, "User ${user.name}", Toast.LENGTH_SHORT).show()
            }

            override fun onUserDelete(user: Users) {
                usersService.removeUser(user)
            }

            override fun onUserMove(user: Users, moveBy: Int) {
                usersService.moveUser(user, moveBy)

            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        usersService.addListener(usersListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener (usersListener)
    }

    private val usersListener:UsersListener = {
        adapter.users = it
    }
}