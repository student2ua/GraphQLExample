package com.tor.graphqlexemple.dao

import com.tor.graphqlexemple.model.Human
import io.ktor.application.Application
import io.ktor.application.log
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import org.slf4j.Logger
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.asResourceStream(): InputStream = this.javaClass::class.java.getResource(this).openStream()
//fun String.ifNull(defaultValue:String?=null): String? =if (this.equals("NULL",true)) {defaultValue}else this

open class HumanImporter : CSVDataImporter(), KoinComponent {

    private val humanDatabase by inject<IHumanStorage>()

    override fun import(log: Logger) {
//        Human
        importFromCsv("/HUMAN1999.csv".asResourceStream()){row->
            try {
                val human = Human(
                        id = row[0].toInt(),
                        inn = toNotNull(row[1]),
                        lastName = row[2],
                        firsName = row[4],
                        middleName = row[5],
                        sex = row[8].toBoolean(),
                        birthday = LocalDate.parse(row[11].trim('"'), DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00")), //"2004-08-14 00:00:00"
                        brothersCount = row[14].toInt(),
                        childrenCount = row[15].toInt(),
                        sportText = toNotNull(row[16]),
                        amateurText = toNotNull(row[17]),
                        phone = toNotNull(row[25]),
                        needHospice = row[27].toBoolean(),
                        isUkrainian = !row[29].toBoolean(),
                        email = toNotNull(row[36]),
                        remark = toNotNull(row[38]),
                        isHumanDismissed = row[39].toBoolean(),
                        personCodeU = toNotNull(row[40]),
                        idPerson = row[41].toIntOrNull(),
                        email2 = toNotNull(row[42]),
                        experienceBegin = if (row[43].equals("NULL")) { null } else LocalDate.parse(row[43])
                )
                humanDatabase.createHuman(human)
            }catch (e:ClassCastException){
                e.printStackTrace()
                log.error(e.localizedMessage)
            }catch (e:Exception){
                log.error(e.message)
            }
        }
        log.info("Import complete")
      }

}
fun Application.importData(){
    HumanImporter().import(log)
}