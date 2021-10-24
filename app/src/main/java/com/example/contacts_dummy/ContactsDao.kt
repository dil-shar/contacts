package com.example.contacts_dummy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.contacts_dummy.Contacts
import java.util.concurrent.Flow

@Dao
interface ContactsDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact : Contacts)



    @Delete
    suspend fun delete(contact : Contacts)

//    @Query("SELECT * from Contacts WHERE id= :key")
//    suspend fun get(key :Int):Contacts?

//    @Query("DELETE  FROM Contacts")
//    fun clear()

    @Query("SELECT * FROM Contact_Table")
    fun getAllContacts() : LiveData<List<Contacts>>

}