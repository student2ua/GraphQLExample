package com.tor.graphqlexemple.dao

import com.tor.graphqlexemple.model.Human
import org.jetbrains.squash.connection.DatabaseConnection
import org.jetbrains.squash.connection.transaction
import org.jetbrains.squash.dialects.h2.H2Connection
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
        sportText = toNotNull(this[HumanEntity.sportText]),
        amateurText = toNotNull(this[HumanEntity.amateurText]),
        phone = toNotNull(this[HumanEntity.phone]),
        needHospice = this[HumanEntity.needHospice],
        isUkrainian = this[HumanEntity.isUkrainian],
        email = toNotNull(this[HumanEntity.email]),
        email2 = toNotNull(this[HumanEntity.email2]),
        remark = toNotNull(this[HumanEntity.remark]),
        isHumanDismissed = this[HumanEntity.isHumanDismissed],
        personCodeU = toNotNull(this[HumanEntity.personCodeU]),
        idPerson = this[HumanEntity.idPerson]?.toInt(),
        experienceBegin = this[HumanEntity.experienceBegin]
)

fun toNotNull(s: String?): String = if (s == null || s.equals("NULL")) {
    ""
} else {
    s
}

class HumanStorageImp(val db: DatabaseConnection = H2Connection.createMemoryConnection()) : IHumanStorage {
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
            it[identificationCode] = toNotNull(human.inn)
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