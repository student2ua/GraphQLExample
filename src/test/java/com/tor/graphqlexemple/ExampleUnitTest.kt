package com.tor.graphqlexemple

import com.tor.graphqlexemple.dao.toNotNULL
import com.tor.graphqlexemple.model.Human
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Example local unit test, which will execute on the development machine (host).
 *https://blog.philipphauer.de/best-practices-unit-testing-kotlin/
 */
class ExampleUnitTest {
    @Test
    fun parse_UFO_line() {
        val line = "1/1/2013,bellingham,wa,us,disk,300,Five Glowing Orbs in the sky,48.7597222,-122.4869444"
        val splitR = line.split("\\s*,\\s*".toRegex())
        println("size $splitR is ${splitR.size}")
        splitR.forEach { s -> println(s) }
        assertTrue(splitR.size == 9)
        val toTypedArray = line.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        println("toTypedArray $toTypedArray size is ${toTypedArray.size}")
        toTypedArray.forEach { s: String -> println(s) }
        assertTrue(splitR.size == 9)
    }

    @Test
    fun parseHumanB() {
        LocalDate.parse("2004-08-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"))
    }

    @Test
    fun parse_Human_line() {
        val line = "167343,NULL,Вишинська,NULL,Людмила,Валеріївна,Л,В,2,2,1,\"2004-08-15 00:00:00\",\"Дцької обл., Ний р-н, с. Прке\",0,0,0,NULL,NULL,349228,NULL,109665,NULL,NULL,NULL,NULL,NULL,0,0,0,1,NULL,NULL,нет,нет,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL"

        val toTypedArray = line.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        println("toTypedArray $toTypedArray size is ${toTypedArray.size}")
        toTypedArray.forEach { s: String -> println(s) }//44
        var row = toTypedArray;
        val human = Human(
                id = row[0].toInt(),
                inn = row[1],
                lastName = row[2],
                firsName = row[4],
                middleName = row[5],
                sex = row[8].toBoolean(),
                birthday = LocalDate.parse(row[11].trim('"'), DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00")), //"2004-08-14 00:00:00"
                brothersCount = row[14].toInt(),
                childrenCount = row[15].toInt(),
                sportText = row[16].toNotNULL(),
                amateurText = row[17].toNotNULL(),
                phone = row[25].toNotNULL(),
                needHospice = row[27].toBoolean(),
                isUkrainian = !row[29].toBoolean(),
                email = row[36].toNotNULL(),
                remark = row[38].toNotNULL(),
                isHumanDismissed = row[39].toBoolean(),
                personCodeU = row[40].toNotNULL(),
                idPerson = row[41].toIntOrNull(),
                email2 = row[42].toNotNULL(),
                experienceBegin = if (row[43].equals("NULL")) {
                    null
                } else LocalDate.parse(row[43])
        )
        println(human)

    }

}
