package com.example.taboocards.db

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class CardsFileReader {

    fun readFromFile() {

        csvReader().open(("com/example/taboocards/text.csv")) {
            readAllAsSequence().forEach { row ->
                //Do something
                println(row) //[a, b, c]
            }
        }

    }

}