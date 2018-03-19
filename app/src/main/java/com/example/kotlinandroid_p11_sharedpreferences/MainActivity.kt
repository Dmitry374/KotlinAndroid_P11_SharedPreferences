package com.example.kotlinandroid_p11_sharedpreferences

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

/*
* https://www.youtube.com/watch?v=wa2fWe0DpaQ&index=11&list=PLk7v1Z2rk4hj2rnU4Lcjs1GFw6Nqt-tlW
* */

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var editTextCity: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        editTextCity = findViewById(R.id.editTextCity)

        retrieveDate()

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            saveData()
        }

    }

    private fun retrieveDate(){
        val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)

        val name = mypref.getString("name", "")
        val city = mypref.getString("city", "")

        editTextName.setText(name)
        editTextCity.setText(city)
    }

    private fun saveData() {
        if (editTextName.text.isEmpty()){
            editTextName.error = "Please enter a name"
            return
        }

        if (editTextCity.text.isEmpty()){
            editTextCity.error = "Please enter your city"
            return
        }

        val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)

        val editor = mypref.edit()

        editor.putString("name", editTextName.text.toString())
        editor.putString("city", editTextCity.text.toString())

        editor.apply()

        Toast.makeText(this, "Date saved", Toast.LENGTH_SHORT).show()
    }
}
