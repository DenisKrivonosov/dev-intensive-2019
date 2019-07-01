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
     class Builder(){
         companion object Builder {
             private var lastId: Int = -1
             private var id: String = "${lastId++}"
             private var firstName: String? = null
             private var lastName: String? = null
             private var avatar: String? = null
             private var rating: Int = 0
             private var respect: Int = 0
             private var lastVisit: Date? = Date()
             private var isOnline: Boolean = false
         }

         fun id(value:String)= apply { id=value }
         fun firstName(value:String)= apply { firstName = value }
         fun lastName(value:String)= apply { lastName = value }
         fun avatar(value:String)= apply { avatar = value }
         fun rating(value:Int)= apply { rating = value }
         fun respect(value:Int)= apply { respect = value }
         fun lastVisit(value:Date?)= apply { lastVisit = value }
         fun isOnline(value:Boolean)= apply { isOnline = value }
         fun build():User{
            var user = User(id, firstName, lastName, avatar, rating, respect, lastVisit , isOnline)
//             id = lastId
             lastId = id.toInt()+1
             id = "${lastId++}"
             firstName = null
             lastName = null
             avatar = null
             rating = 0
             respect = 0
             lastVisit = Date()
             isOnline = false
             return user
        }
    }
}