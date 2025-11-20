package com.example.sharingapp.ui

import android.app.Application
import com.example.sharingapp.data.AppContainer
import com.example.sharingapp.data.AppContainerImpl

// Application is a subclass of context
class SharingApplication : Application(){

    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()

        // the container contains a context object and
        // provides app context to the rest of the program
        // There is only one container for the entire app
        // TODO: replace with actual implementation later b/c this uses fake data
        container = AppContainerImpl(this)
    }
}