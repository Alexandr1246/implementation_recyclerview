package com.example.train.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.train.UserActionListener
import com.example.train.UsersAdapter
import com.example.train.databinding.FragmentUsersListBinding
import com.example.train.model.Users

class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding
    private lateinit var adapter: UsersAdapter

    private val viewModel: UsersListViewModel by viewModels{factory()} // by, as где о них можно почитать как и когда их использвать?
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(object : UserActionListener {

            override fun onUserMove(user: Users, moveBy: Int) {
                viewModel.moveUser(user, moveBy)
            }
            override fun onUserDetails(user: Users) {
                navigator().showDetails(user)

            }

            override fun onUserDelete(user: Users) {
                viewModel.deleteUser(user)
            }
        })

        viewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.users = it } )

        val layoutManager = LinearLayoutManager(requireContext()) // почему тут указываем requireContext? откуда взялся этот метод?
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root

    }

}