package com.demirli.a16validationwithregex

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var email: String? = null
    private var username: String? = null
    private var password: String? = null

    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var logArrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logArrayList = arrayListOf()

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,logArrayList)
        listView.adapter = adapter

        login_btn.setOnClickListener {
            login()
        }

        clear_btn.setOnClickListener {
            clearText()
        }
    }

    fun login(){
        email = email_et.text.toString()
        username = username_et.text.toString()
        password = password_et.text.toString()

        checkSpacesAndRegex()
        adapter.notifyDataSetChanged()
    }

    fun clearText(){
        email_et.setText("")
        username_et.setText("")
        password_et.setText("")

        logArrayList.clear()
        returnNormalColorInSapaces()

        adapter.notifyDataSetChanged()
    }

    fun checkSpacesAndRegex(){
        logArrayList.clear()
        returnNormalColorInSapaces()

        if(username == ""){
            logArrayList.add("*Your username field is empty.")
            username_et.setBackgroundColor(Color.YELLOW)
        }
        if(password == ""){
            logArrayList.add("*Your password field is empty.")
            password_et.setBackgroundColor(Color.YELLOW)
        }
        if(email == ""){
            logArrayList.add("*Your email field is empty.")
            email_et.setBackgroundColor(Color.YELLOW)
        }
        if(username != "" && password != "" && email != ""){
            if(checkRegex() == true){
                Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                username_et.setBackgroundColor(Color.GREEN)
                password_et.setBackgroundColor(Color.GREEN)
                email_et.setBackgroundColor(Color.GREEN)
            }
        }
    }

    fun checkRegex(): Boolean{
        var check = true
        val spaceRegex = "^(?=.*[\\s]{1})".toRegex()
        val capitalRegex = "^(?=.*[A-Z]{5})".toRegex()
        val symbolRegex = "^(?=.*[!@#\$%^&*]{6})".toRegex()
        val hyphensRegex = "^(?=.*[-]{2})".toRegex()
        val mailRegex = "(\\W|^)[\\w.+\\-]*@gmail\\.com(\\W|\$)".toRegex()

        if(!mailRegex.containsMatchIn(email!!)){
            logArrayList.add("*Enter you gmail. (ex: @gmail.com)")
            email_et.setBackgroundColor(Color.YELLOW)
            check = false
        }

        if(spaceRegex.containsMatchIn(username!!)){
            logArrayList.add("*You have space in username field.")
            username_et.setBackgroundColor(Color.YELLOW)
            check = false
        }

        if(!capitalRegex.containsMatchIn(password!!)){
            logArrayList.add("*Minimum 5 capital letters required in password field.")
            password_et.setBackgroundColor(Color.YELLOW)
            check = false
        }

        if(!symbolRegex.containsMatchIn(password!!)){
            logArrayList.add("*Minimum 6 symbols required in password field.")
            password_et.setBackgroundColor(Color.YELLOW)
            check = false
        }

        if(!hyphensRegex.containsMatchIn(password!!)){
            logArrayList.add("*Minimum 2 hyphens required in password field.")
            password_et.setBackgroundColor(Color.YELLOW)
            check = false
        }
        return check
    }

    fun returnNormalColorInSapaces(){
        username_et.setBackgroundColor(Color.WHITE)
        password_et.setBackgroundColor(Color.WHITE)
        email_et.setBackgroundColor(Color.WHITE)
    }
}
