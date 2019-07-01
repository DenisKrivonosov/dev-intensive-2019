package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id: String,
    var firstName: String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit:Date? = Date(),
    val isOnline:Boolean  =false
)
{
    var introbit:String

    constructor(id:String,firstName: String?,lastName: String?):this(
        id=id,
        firstName = firstName,
        lastName = lastName,
        avatar = null)
    constructor(id: String):this(
        id=id,
        firstName = "John",
        lastName = "Doe")
    init {
        introbit = getIntro()
        println("it's Alive!!! \n"+
                "${if(lastName==="Doe") "His name is  firstname =$firstName lastname = $lastName" else "And his name is firstname=$firstName lastname=$lastName"} \n"+
                "${getIntro()}")
    }

    private fun getIntro(): String ="""
        sxdhfgsdhfgsd
        ewrywerlklk!!
        ${"\n \n"}
        $firstName $lastName
    """.trimIndent()

    fun printMe() = println("""
            id: $id,
            firstName: $firstName,
            lastName: $lastName,
            avatar: $avatar,
            rating: $rating,
            respect: $respect,
            lastVisit: $lastVisit,
            isOnline:$isOnline
        """.trimIndent())

    companion object Factory{
        private var lastId=-1
        fun makeUser(fullName:String?):User{
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id= "$lastId", firstName = firstName,lastName = lastName)
        }
    }
}