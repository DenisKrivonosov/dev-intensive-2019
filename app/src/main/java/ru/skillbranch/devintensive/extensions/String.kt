package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.truncate(length:Int=16):String{
    return when{
        this.trim().length in 0..length->"${this.substring(0,length).trim()}"
        else->"${this.substring(0, length).trim()}..."
    }
}
fun String.stripHtml():String{
    var tagsString = this
        .replace(
        "<.*?>".toRegex(),
        ""
        )
        .replace("&.*?;".toRegex(),
        "")
        .replace("\\s+".toRegex(), " ")
    return tagsString
}