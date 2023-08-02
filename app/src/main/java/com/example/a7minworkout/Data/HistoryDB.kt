package com.example.a7minworkout.Data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDB :RoomDatabase(){
    abstract fun historyDao():HistoryDao

    companion object{
        @Volatile
        private var INSTANCE:HistoryDB?=null
        fun getInstance(context: Context):HistoryDB{
            synchronized(this){
                var instance= INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDB::class.java,
                        "history_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}