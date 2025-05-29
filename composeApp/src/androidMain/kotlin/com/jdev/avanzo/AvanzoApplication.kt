package com.jdev.avanzo

import android.app.Application
import com.jdev.avanzo.di.initKoin
import org.koin.android.ext.koin.androidContext

class AvanzoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@AvanzoApplication)
            modules()
        }

    }

}