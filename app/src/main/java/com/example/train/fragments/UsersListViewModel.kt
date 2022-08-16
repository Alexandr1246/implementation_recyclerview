package com.example.train.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.model.Users
import com.example.train.model.UsersListener
import com.example.train.model.UsersService

class UsersListViewModel(
    private val usersService: UsersService
) : ViewModel() {

    private val _users = MutableLiveData<List<Users>>()
    val users: LiveData<List<Users>> = _users

    private val listener : UsersListener = {
        _users.value = it
    }

    init {
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        usersService.removeListener(listener)
    }

    fun loadUsers() {
        usersService.addListener(listener)
    }

    fun moveUser(user: Users, moveBy: Int) {
        usersService.moveUser(user, moveBy)
    }

    fun deleteUser(user: Users) {
        usersService.removeUser(user)
    }


}