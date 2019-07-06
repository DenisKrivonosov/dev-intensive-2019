package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
//
const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}
fun Date.add(value:Int, units:TimeUnits=TimeUnits.SECOND):Date{
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value* SECOND
        TimeUnits.MINUTE -> value* MINUTE
        TimeUnits.HOUR -> value* HOUR
        TimeUnits.DAY -> value* DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String {

    var time = this.time
    val milliSecondsFiff = date.getTime() - time
    var secondsDiff = milliSecondsFiff/1000
    if(secondsDiff<0){secondsDiff-=1}
//    val minutesDiff = milliSecondsFiff.toInt()/1000*60
    println(secondsDiff)
    val range1 = arrayOf(0L,5L,6L,7L,8L,9L)
    val range2 = arrayOf(1L)
    val range3 = arrayOf(2L, 3L, 4L)
    val humanized = when{
        secondsDiff in 0..1-> "только что"
        secondsDiff in 1..45-> "несколько секунд назад"
        secondsDiff in 45..75-> "минуту назад"
        secondsDiff in 75..45*60 && secondsDiff>=600 && secondsDiff<=1200 -> "${secondsDiff/60} минут назад"
        secondsDiff in 75..45*60 && range1.contains(secondsDiff/60%10) -> "${secondsDiff/60} минут назад"
        secondsDiff in 75..45*60 && range2.contains(secondsDiff/60%10) -> "${secondsDiff/60} минуту назад"
        secondsDiff in 75..45*60 && range3.contains(secondsDiff/60%10) -> "${secondsDiff/60} минуты назад"
        secondsDiff in 45*60..75*60 -> "час назад"
        secondsDiff in 75*60..22*60*60 && secondsDiff>=10*66*66 && secondsDiff<=20*60*60 -> "${secondsDiff/(60*60)} часов назад"
        secondsDiff in 75*60..22*60*60 && range1.contains(secondsDiff/(60*60)%10) -> "${secondsDiff/(60*60)} часов назад"
        secondsDiff in 75*60..22*60*60 && range2.contains(secondsDiff/(60*60)%10) -> "${secondsDiff/(60*60)} час назад"
        secondsDiff in 75*60..22*60*60 && range3.contains(secondsDiff/(60*60)%10) -> "${secondsDiff/(60*60)} часа назад"
        secondsDiff in 22*60*60..26*60*60 -> "день назад"
        secondsDiff in 26*60*60..360*24*60*60 && secondsDiff>=10*24*60*60 && secondsDiff<=20*24*60*60 -> "${secondsDiff/(24*60*60)} дней назад"
        secondsDiff in 26*60*60..360*24*60*60 && range1.contains(secondsDiff/(24*60*60)%10) -> "${secondsDiff/(24*60*60)} дней назад"
        secondsDiff in 26*60*60..360*24*60*60 && range2.contains(secondsDiff/(24*60*60)%10) -> "${secondsDiff/(24*60*60)} день назад"
        secondsDiff in 26*60*60..360*24*60*60 && range3.contains(secondsDiff/(24*60*60)%10) -> "${secondsDiff/(24*60*60)} дня назад"
        secondsDiff>360*24*60*60 -> "более года назад"

        secondsDiff<0 && Math.abs(secondsDiff) in 0..1-> "только что"
        secondsDiff<0 && Math.abs(secondsDiff) in 1..45-> "чере несколько секунд "
        secondsDiff<0 && Math.abs(secondsDiff) in 45..75-> "через минуту"
        secondsDiff<0 && Math.abs(secondsDiff) in 75..45*60 && secondsDiff<=-600 && secondsDiff>=-1200 -> "через ${Math.abs(secondsDiff)/60} минут"
        secondsDiff<0 && Math.abs(secondsDiff) in 75..45*60 && range1.contains(Math.abs(secondsDiff)/60%10) -> "через ${Math.abs(secondsDiff)/60} минут"
        secondsDiff<0 && Math.abs(secondsDiff) in 75..45*60 && range2.contains(Math.abs(secondsDiff)/60%10) -> "через ${Math.abs(secondsDiff)/60} минуту"
        secondsDiff<0 && Math.abs(secondsDiff) in 75..45*60 && range3.contains(Math.abs(secondsDiff)/60%10) -> "через ${Math.abs(secondsDiff)/60} минуты"
        secondsDiff<0 && Math.abs(secondsDiff) in 45*60..75*60 -> "час назад"
        secondsDiff<0 && Math.abs(secondsDiff) in 75*60..22*60*60 && Math.abs(secondsDiff)<=-10*66*66 && Math.abs(secondsDiff)>=-20*60*60 -> "через ${Math.abs(secondsDiff)/(60*60)} часов"
        secondsDiff<0 && Math.abs(secondsDiff) in 75*60..22*60*60 && range1.contains(Math.abs(secondsDiff)/(60*60)%10) -> "через ${Math.abs(secondsDiff)/(60*60)} часов"
        secondsDiff<0 && Math.abs(secondsDiff) in 75*60..22*60*60 && range2.contains(Math.abs(secondsDiff)/(60*60)%10) -> "через ${Math.abs(secondsDiff)/(60*60)} час"
        secondsDiff<0 && Math.abs(secondsDiff) in 75*60..22*60*60 && range3.contains(Math.abs(secondsDiff)/(60*60)%10) -> "через ${Math.abs(secondsDiff)/(60*60)} часа"
        secondsDiff<0 && Math.abs(secondsDiff) in 22*60*60..26*60*60 -> "день назад"
        secondsDiff<0 && Math.abs(secondsDiff) in 26*60*60..360*24*60*60 && Math.abs(secondsDiff)<=-10*24*60*60 && Math.abs(secondsDiff)>=-20*24*60*60 -> "через ${Math.abs(secondsDiff)/(24*60*60)} дней"
        secondsDiff<0 && Math.abs(secondsDiff) in 26*60*60..360*24*60*60 && range1.contains(Math.abs(secondsDiff)/(24*60*60)%10) -> "через ${Math.abs(secondsDiff)/(24*60*60)} дней"
        secondsDiff<0 && Math.abs(secondsDiff) in 26*60*60..360*24*60*60 && range2.contains(Math.abs(secondsDiff)/(24*60*60)%10) -> "через ${Math.abs(secondsDiff)/(24*60*60)} день"
        secondsDiff<0 && Math.abs(secondsDiff) in 26*60*60..360*24*60*60 && range3.contains(Math.abs(secondsDiff)/(24*60*60)%10) -> "через ${Math.abs(secondsDiff)/(24*60*60)} дня"
        secondsDiff<0 && Math.abs(secondsDiff)>360*24*60*60 -> "более чем через год"
        else -> "$secondsDiff"
    }
    return humanized
}

enum class TimeUnits{
    SECOND, MINUTE, HOUR, DAY;
    fun plural(value:Int): String{
        return when(this){
            SECOND -> "$value ${getPlurals(value, Triple("секунд","секунду","секунды"))}"
            MINUTE -> "$value ${getPlurals(value, Triple("минут","минуту","минуты"))}"
            HOUR -> "$value ${getPlurals(value, Triple("часов","час","часа"))}"
            DAY -> "$value ${getPlurals(value, Triple("дней","день","дня"))}"
        }
    }
}



fun getPlurals(value: Int, dict: Triple<String, String, String>): String {
    return when {
        value % 100 in 5..20 -> dict.first
        value % 10 == 1 -> dict.second
        value % 10 in 2..4 -> dict.third
        else -> dict.first
    }
}