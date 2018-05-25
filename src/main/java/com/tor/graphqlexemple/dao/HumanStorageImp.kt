package com.tor.graphqlexemple.dao

import com.tor.graphqlexemple.model.Human
import org.jetbrains.squash.connection.DatabaseConnection
import org.jetbrains.squash.connection.transaction
import org.jetbrains.squash.expressions.eq
import org.jetbrains.squash.query.*
import org.jetbrains.squash.results.ResultRow
import org.jetbrains.squash.results.get
import org.jetbrains.squash.schema.create
import org.jetbrains.squash.statements.insertInto
import org.jetbrains.squash.statements.values

/**
 * Created by tor on 05.03.2018.
 */

fun ResultRow.toHuman() = Human(
        id = this[HumanEntity.humanid],
        inn = this[HumanEntity.identificationCode],
        lastName = this[HumanEntity.lastName],
        firsName = this[HumanEntity.firstName],
        middleName = this[HumanEntity.middleName],
        sex = this[HumanEntity.sex],
        birthday = this[HumanEntity.birthday],
        brothersCount = this[HumanEntity.brothersCount],
        childrenCount = this[HumanEntity.childrenCount],
        sportText = this[HumanEntity.sportText].toNotNULL(),
        amateurText = this[HumanEntity.amateurText].toNotNULL(),
        phone = this[HumanEntity.phone].toNotNULL(),
        needHospice = this[HumanEntity.needHospice],
        isUkrainian = this[HumanEntity.isUkrainian],
        email = this[HumanEntity.email].toNotNULL(),
        email2 = this[HumanEntity.email2].toNotNULL(),
        remark = this[HumanEntity.remark].toNotNULL(),
        isHumanDismissed = this[HumanEntity.isHumanDismissed],
        personCodeU = this[HumanEntity.personCodeU].toNotNULL(),
        idPerson = this[HumanEntity.idPerson]?.toInt(),
        experienceBegin = this[HumanEntity.experienceBegin]
)

/*fun toNotNull(s: String?): String = if (s == null || s.equals("NULL")) {
    ""
} else {
    s
}*/

fun String?.toNotNULL(): String = if (this == null || this.equals("NULL")) {
    ""
} else {
    this
}

class HumanStorageImp(val db: DatabaseConnection) : IHumanStorage {
    override fun getHumanAll(size: Long) = db.transaction {
        from(HumanEntity)
                .select()
                .orderBy(HumanEntity.birthday, false)
                .limit(size)
                .execute()
                .map { it.toHuman() }
                .toList()
    }

    override fun createHuman(human: Human) = db.transaction {
        insertInto(HumanEntity).values {
            it[humanid] = human.id
            it[identificationCode] = human.inn.toNotNULL()// human.inn ?:""
            it[lastName] = human.lastName
            it[firstName] = human.firsName
            it[middleName] = human.middleName
            it[sex] = human.sex
            it[birthday] = human.birthday
            it[brothersCount] = human.brothersCount
            it[childrenCount] = human.childrenCount
            it[sportText] = human.sportText
            it[amateurText] = human.amateurText
            it[phone] = human.phone
            it[needHospice] = human.needHospice
            it[isUkrainian] = human.isUkrainian
            it[email] = human.email
            it[email2] = human.email2
            it[remark] = human.remark
            it[isHumanDismissed] = human.isHumanDismissed
            it[personCodeU] = human.personCodeU
            it[idPerson] = human.idPerson?.toString()
            it[experienceBegin] = human.experienceBegin
        }.execute()
        human.id
    }

    override fun getHuman(id: Int) = db.transaction {
        val row = from(HumanEntity).where(HumanEntity.humanid eq id).execute().singleOrNull()
        row?.toHuman()
    }

    init {
        db.transaction { databaseSchema().create(HumanEntity) }
    }


    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}