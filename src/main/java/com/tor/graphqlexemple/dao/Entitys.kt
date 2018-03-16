package com.tor.graphqlexemple.dao

import org.jetbrains.squash.definition.*

/**
 * Created by tor on 05.03.2018.
 */

//CREATE UNIQUE INDEX FULLNAME_IDX ON HUMAN ( SYS_NC00045$ );
//CREATE UNIQUE INDEX HUMAN_ADRESS ON HUMAN ( ADDRESS1, ADDRESS2, ADDRESS3, BIRTHDAY );
//CREATE UNIQUE INDEX HUMAN_LASTNAME_IDX ON HUMAN ( LASTNAME );

object HumanEntity : TableDefinition("Human.Human") {
    val humanid = integer("HUMANID").autoIncrement().primaryKey()
    val identificationCode = varchar("IDENTIFICATIONCODE", 20)
    val lastName = varchar("LASTNAME", 60).index()
    val lastNameOld = varchar("LASTNAME2", 60).nullable()
    val firstName = varchar("FIRSTNAME", 50)
    val middleName = varchar("MIDDLENAME", 50)
    val firstRus = varchar("FIRSTRUS", 3).nullable()
    val middleRus = varchar("MIDDLERUS", 3).nullable()
    val sex = bool("SEX").default(true)
    val sitezenShip = integer("CITIZENSHIP").default(2)
    val nationality = integer("NATIONALITY").default(0)
    val birthday = date("BIRTHDAY")
    val birthplace = varchar("BIRTHPLACE", 300).nullable()
    val familyStatus = integer("FAMILY_STATUS").default(0)
    val brothersCount = integer("BROTHERS_COUNT").default(0)
    val childrenCount = integer("CHILDREN_COUNT").default(0)
    val sportText = varchar("SPORT_TEXT", 300).nullable()
    val amateurText = varchar("AMATEUR_TEXT", 300).nullable()
    val address1 = integer("ADDRESS1").nullable()
    val address2 = integer("ADDRESS2").nullable()
    val citizenDocument = integer("CITIZEN_DOCUMENT").nullable()
    val workBook = integer("WORK_BOOK").nullable()

    val studyinglanguage = integer("STUDYINGLANGUAGE").nullable()
    val militaryId = integer("MILITARYID").nullable()
    val ovirId = integer("OVIRID").nullable()
    val phone = varchar("PHONE", 50).nullable()
    val callup = integer("CALLUP").default(0)
    val needHospice = bool("NEEDHOSPICE").default(false)
    val directionFromEnterprise = integer("DIRECTION_FROM_ENTERPRISE").default(0)
    val isUkrainian = bool("IS_UKRAINIAN").default(true)
    val ADDRESS3 = integer("ADDRESS3").nullable()
    val FORLANGUAGEINUNIVER = integer("FORLANGUAGEINUNIVER").nullable()
    val LAST_ACADEMIC_DEGREE = varchar("LAST_ACADEMIC_DEGREE", 50).nullable()
    val LAST_ACADEMIC_TITLE = varchar("LAST_ACADEMIC_TITLE", 50).nullable()
    val TRADE_UNION_ID = integer("TRADE_UNION_ID").nullable()

    val email = varchar("EMAIL", 50).nullable()
    val insuranceId = integer("INSURANCEID").nullable()
    val remark = varchar("REMARK", 300).nullable()
    val isHumanDismissed = bool("IS_HUMANDISMISSED").default(false)
    val personCodeU = varchar("PERSON_CODE_U", 100).nullable()
    val idPerson = varchar("ID_PERSON", 100).nullable()
    val email2 = varchar("EMAIL2", 50).nullable()
    val experienceBegin = date("EXPERIENCE_BEGIN").nullable()
}