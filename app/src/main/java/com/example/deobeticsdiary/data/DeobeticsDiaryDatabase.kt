package com.example.deobeticsdiary.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.deobeticsdiary.data.dao.NoteDao
import com.example.deobeticsdiary.data.entity.Note
import com.example.deobeticsdiary.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Note::class], version = 1)
abstract class DeobeticsDiaryDatabase: RoomDatabase()  {

    abstract fun noteDao(): NoteDao

    class Callback @Inject constructor(
        private val database: Provider<DeobeticsDiaryDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val noteDao = database.get().noteDao()

            applicationScope.launch {

            }
        }
    }
}