package uz.itech.mentalarifmetika.model

import java.io.Serializable

data class Item(
    val itemId:Int,
    val itemList:ArrayList<Int>
):Serializable