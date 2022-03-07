package uz.itech.mentalarifmetika.model

import java.io.Serializable

data class Model(
    val count:String,
    val row:String,
    val time:Int,
    val complex:Byte
):Serializable
