package me.dio.android.hilt.model

import javax.inject.Inject

//class HelloModel @Inject constructor() { // without module
//    val text : String = "Hello World!"
//}

//class HelloModel(val text : String = "Hello World!") // with module and without interface

class HelloModelImp @Inject constructor () : HelloModel { // with module and interface
    override val text : String = "Hello from Impl!"
}
