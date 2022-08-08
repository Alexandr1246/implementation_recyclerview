package com.example.train

import android.app.Application
import com.example.train.model.UsersService

class App: Application() {

    val usersService = UsersService()

}