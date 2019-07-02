package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        val parts:List<String>? = fullName?.split(" ")
        println("parts: $parts")
        val firstName = if (parts?.getOrNull(0)=="") null else {parts?.getOrNull(0)}
        val lastName = if (parts?.getOrNull(1)=="") null else {parts?.getOrNull(1)}

        return firstName to lastName
    }
    fun transliteration(payload:String, divider:String=" "):String{
        var translatedPayload =""
        for (char in payload){
            var shouldCapitalise = false
            if (char.isUpperCase()){
               shouldCapitalise = true
            }

            var translatedChar=when(char.toString().toLowerCase()){
                " "->divider
                "а" -> "a"

                "б" ->"b"

                "в"-> "v"

                "г"-> "g"

                "д"-> "d"

                "е"->"e"

                "ё"->"e"

                "ж" ->"zh"

                "з"->"z"

                "и" -> "i"

                "й"-> "i"

                "к"->"k"

                "л"-> "l"

                "м"->"m"

                "н"->"n"

                "о"->"o"

                "п"->"p"

                "р"->"r"

                "с"->"s"

                "т"->"t"

                "у"-> "u"

                "ф"-> "f"

                "х"-> "h"

                "ц"-> "c"

                "ч"->"ch"

                "ш"->"sh"

                "щ"-> "sh'"

                "ъ"-> ""

                "ы"-> "i"

                "ь"-> ""

                "э"-> "e"

                "ю"-> "yu"

                "я"-> "ya"
                else ->char.toString()
            }
            translatedPayload += if (shouldCapitalise)translatedChar.capitalize() else translatedChar
        }
        return translatedPayload
    }
    fun toInitials(firstName:String?, lastName:String?):String?{
        val firstLetter = if(firstName?.trim()?.length==0) "" else firstName?.get(0)?.toUpperCase()?:""
        val secondLetter = if(lastName?.trim()?.length==0) "" else lastName?.get(0)?.toUpperCase()?:""
        var initials = if("$firstLetter$secondLetter"=="") null else{"$firstLetter$secondLetter"}
        return initials
    }
}