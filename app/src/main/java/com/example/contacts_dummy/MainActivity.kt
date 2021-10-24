package com.example.contacts_dummy

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() ,IContactsRVAdapter{

    lateinit var viewModel: ContactViewModel
    private lateinit var input:EditText
    private lateinit var phoneNo:EditText
    lateinit var submit :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input = findViewById(R.id.name)
        phoneNo  = findViewById(R.id.getPhoneNoHere)
        submit = findViewById(R.id.button)

        submit.setOnClickListener{
            val tt = input.text.toString()
            val pn = phoneNo.text.toString()
            if(tt.isNotEmpty() && pn.isNotEmpty() && pn.contains("[1-9]") && pn.length==10){
                viewModel.insertContact(Contacts(tt,pn))
            }else{
                Toast.makeText(applicationContext, "Enter a valid Contact number", Toast.LENGTH_SHORT).show()
            }
        }


        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactsRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ContactViewModel::class.java)
        viewModel.allContacts.observe(this, Observer {list ->
          list?.let{
            adapter.updateList(it)
              input.setText("")
              phoneNo.setText("")
          }
        })


    }
    override fun onItemClicked(contact :Contacts){
        viewModel.deleteNode(contact)
    }

    override fun onCallClicked(contact: Contacts) {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),
               400)
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.phoneno))
            startActivity(intent)
        }else{
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.phoneno))
            startActivity(intent)
            Toast.makeText(applicationContext, "Calling ${contact.name}", Toast.LENGTH_SHORT).show()
        }


        Toast.makeText(applicationContext, "Calling dbfnr", Toast.LENGTH_SHORT).show()


    }

}
