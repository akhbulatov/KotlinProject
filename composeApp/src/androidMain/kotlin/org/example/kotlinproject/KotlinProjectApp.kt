package org.example.kotlinproject

import android.app.Application
import di.CommonFactory
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class KotlinProjectApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLogger()
    }

    private fun initLogger() {
        Napier.base(DebugAntilog())
    }

    companion object {
        private lateinit var instance: KotlinProjectApp

        val commonFactory: CommonFactory by lazy {
            CommonFactory()
        }
    }
}