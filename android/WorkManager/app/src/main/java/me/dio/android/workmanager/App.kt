package me.dio.android.workmanager

import android.app.Application
import me.dio.android.workmanager.data.datasource.VideosDatasource

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        VideosDatasource.setFromFile(assets)
    }

}
