package com.example.train

import com.example.train.model.Users

interface Navigator {

    fun showDetails(users: Users)


    fun goBack()


    fun showToast(massageRes: Int)

}