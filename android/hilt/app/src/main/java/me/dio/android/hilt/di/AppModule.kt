package me.dio.android.hilt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.dio.android.hilt.model.HelloModel
import me.dio.android.hilt.model.HelloModelImp

//@Module // no view model
//@InstallIn(SingletonComponent::class) // no viewmodel
// object AppModule { // without interface
abstract class AppModule { // with interface

//    @Provides // without interface
//    fun provideHelloModel() : HelloModel {
//        return HelloModel(text = "Hello from module")
//    }

//    @Binds // with interface and no viewmodel
    abstract fun bindsHelloModel(model: HelloModelImp) : HelloModel

}
