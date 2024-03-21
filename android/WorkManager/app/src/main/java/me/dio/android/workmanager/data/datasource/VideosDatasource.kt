package me.dio.android.workmanager.data.datasource

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.dio.android.workmanager.data.model.Video

object VideosDatasource {
    private val list by lazy { arrayListOf<Video>() }

    fun setFromFile(assetManager: AssetManager) {
        val videosFromFile = Gson().fromFile<List<Video>>(assetManager, "videos.json")
        list.addAll(videosFromFile)
    }

    private inline fun <reified T> Gson.fromFile(assetManager: AssetManager, filename: String): T {
        return fromJson<T>(
            assetManager.open(filename).bufferedReader(),
            object : TypeToken<T>() {}.type
        )
    }
}
