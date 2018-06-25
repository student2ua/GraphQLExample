package com.tor.graphqlexemple.di

import com.tor.graphqlexemple.AppSchema
import com.tor.graphqlexemple.dao.HumanStorageImp
import com.tor.graphqlexemple.dao.IHumanStorage
import org.jetbrains.squash.dialects.h2.H2Connection
import org.koin.dsl.module.applicationContext

val mainModule = applicationContext {
    provide { AppSchema(get()) }
    provide { HumanStorageImp(get()) as IHumanStorage }
    provide { H2Connection.createMemoryConnection() /*as DatabaseConnection*/ }
}