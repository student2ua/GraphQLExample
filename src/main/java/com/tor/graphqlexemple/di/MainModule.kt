package com.tor.graphqlexemple.di

import com.tor.graphqlexemple.AppSchema
import com.tor.graphqlexemple.dao.HumanStorageImp
import com.tor.graphqlexemple.dao.IHumanStorage
import org.koin.dsl.module.applicationContext

val mainModule = applicationContext {
    provide { AppSchema(get()) }
    provide { HumanStorageImp() as IHumanStorage }
}