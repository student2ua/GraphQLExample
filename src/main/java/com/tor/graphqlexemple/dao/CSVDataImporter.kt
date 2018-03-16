package com.tor.graphqlexemple.dao

import org.slf4j.Logger
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

abstract class CSVDataImporter {
    abstract fun import(log: Logger)
    fun importFromCsv(inputStream: InputStream, consumer: (Array<String>) -> Unit) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream,Charsets.UTF_8))
        try {
            return bufferedReader.useLines { lines ->
                lines.drop(1).forEach { line ->
                    consumer(line.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("failed Import", e)
        }
    }
}