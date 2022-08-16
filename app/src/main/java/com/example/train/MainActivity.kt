package com.example.train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.train.databinding.ActivityMainBinding
import com.example.train.fragments.UsersDetailsFragment
import com.example.train.fragments.UsersListFragment
import com.example.train.model.Users
import com.example.train.model.UsersListener
import com.example.train.model.UsersService

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction().
            add(R.id.fragmentContainer, UsersListFragment()).
            commit()
        }
    }

    override fun goBack() {
        onBackPressed()

    }

    override fun showDetails(users: Users) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer, UsersDetailsFragment.newInstance(users.id)).commit()

    }

    override fun showToast(massageRes: Int) {
        Toast.makeText(this, massageRes, Toast.LENGTH_SHORT).show()
    }
}