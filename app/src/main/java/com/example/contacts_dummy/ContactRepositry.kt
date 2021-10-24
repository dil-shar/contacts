package com.example.contacts_dummy

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Flow

class ContactRepositry(private val contactsDao : ContactsDao) {
    val allContacts: LiveData<List<Contacts>> = contactsDao.getAllContacts()


    suspend fun insert(contact :Contacts){
        contactsDao.insert(contact)
    }
    suspend fun delete(contact: Contacts){
        contactsDao.delete(contact)
    }
}