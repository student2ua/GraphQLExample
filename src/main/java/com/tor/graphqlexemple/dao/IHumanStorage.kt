package com.tor.graphqlexemple.dao

import com.tor.graphqlexemple.model.Human
import java.io.Closeable

/**
 * Created by tor on 05.03.2018.
 */

interface IHumanStorage :Closeable{
    fun createHuman(human: Human): Int
    fun getHuman(id: Int): Human?
    fun getHumanAll(size: Long): List<Human>
}