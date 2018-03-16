package com.tor.graphqlexemple

import com.github.pgutkowski.kgraphql.KGraphQL
import com.tor.graphqlexemple.dao.IHumanStorage
import com.tor.graphqlexemple.model.Human
import java.time.LocalDate

/**
 * Created by tor on 05.03.2018.
 */

class AppSchema(private val storage: IHumanStorage) {
    val schema = KGraphQL.schema {
        configure {
            useDefaultPrettyPrinter = true
        }
        stringScalar<LocalDate> {
            serialize = { date -> date.toString() }
            deserialize = { dateString -> LocalDate.parse(dateString) }

        }
        query("humans") {
            resolver { size: Long -> storage.getHumanAll(size) }.withArgs {
                arg<Int> { name = "size";defaultValue = 10 }
            }
        }
        query("human") {
            resolver { id: Int ->
                storage.getHuman(id) ?: throw NotFoundException("human with id:$id does not exist")
            }
        }
        type<Human> { description = "human eee" }
    }
}