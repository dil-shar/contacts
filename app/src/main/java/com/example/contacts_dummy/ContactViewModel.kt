package com.example.contacts_dummy

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ContactViewModel(application: Application) : AndroidViewModel(application){
   val allContacts : LiveData<List<Contacts>>
    private val repositry :ContactRepositry

    init{
        val dao = ContactRoomDatabase.getDataBase(application).getContactDao()
        repositry = ContactRepositry(dao)
        allContacts = repositry.allContacts
    }
    fun insertContact(contact : Contacts)  = viewModelScope.launch(Dispatchers.IO) {
        repositry.insert(contact)
    }
  fun deleteNode(contact :Contacts)  = viewModelScope.launch(Dispatchers.IO){
      repositry.delete(contact)
  }
}
