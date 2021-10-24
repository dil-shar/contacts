package com.example.contacts_dummy

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ContactsRVAdapter(private val context: Context, private val listener:IContactsRVAdapter): RecyclerView.Adapter<ContactsRVAdapter.ContactsViewHolder>() {
    private val allNotes = ArrayList<Contacts>()
    inner class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.Nameviewid)
        val delete: ImageView = itemView.findViewById(R.id.delete)
        val callNo : TextView = itemView.findViewById(R.id.PhoneNoId)
        val call   :ImageView = itemView.findViewById(R.id.phonecallid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val viewHolder = ContactsViewHolder(LayoutInflater.from(context).inflate(R.layout.single_contact,parent,false))
        viewHolder.delete.setOnClickListener{

            listener.onItemClicked(allNotes[viewHolder.adapterPosition])

        }
        viewHolder.call.setOnClickListener{
            listener.onCallClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
       val currentContact = allNotes[position]
        holder.textView.text = currentContact.name
        holder.callNo.text = currentContact.phoneno
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(contactList:List<Contacts>){
        allNotes.clear()
        allNotes.addAll(contactList)

        notifyDataSetChanged()
    }

}
interface IContactsRVAdapter{
    fun onItemClicked(contact :Contacts){}
    fun onCallClicked(contact:Contacts){}
}