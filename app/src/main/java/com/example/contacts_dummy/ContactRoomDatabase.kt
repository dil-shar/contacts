package com.example.contacts_dummy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class],version = 2,exportSchema = false)
abstract class ContactRoomDatabase:RoomDatabase() {
    abstract fun getContactDao(): ContactsDao

    companion object{

        @Volatile
        private  var INSTANCE : ContactRoomDatabase? = null

        fun getDataBase(context :Context):ContactRoomDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(context.applicationContext,
                    ContactRoomDatabase::class.java,"Contacts").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }

}