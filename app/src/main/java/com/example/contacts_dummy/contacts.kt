package com.example.contacts_dummy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact_Table")
 class Contacts(
    @ColumnInfo(name = "ContactName")
    var name  : String,
//
    @ColumnInfo(name = "PhoneNo")
    var phoneno :String
    ){
  @PrimaryKey(autoGenerate = true) var id = 0
 }