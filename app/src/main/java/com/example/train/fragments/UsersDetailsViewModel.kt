package com.example.train.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.UserNotFoundException
import com.example.train.model.UserDetails
import com.example.train.model.UsersService

class UsersDetailsViewModel(private val usersService: UsersService): ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()
    val userDetails :LiveData<UserDetails> = _userDetails

    fun loadUser(userId: Long){
        if (_userDetails.value!=null) return

        try {
            _userDetails.value = usersService.getById(userId)
        }catch (e: UserNotFoundException)
        {
            e.printStackTrace()
        }
    }

    fun deleteUser(){

        val userDetails = this.userDetails.value ?: return

        usersService.removeUser(userDetails.user)
    }
}