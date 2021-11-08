package com.example.mykotlinapp

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerButton()
    }


    fun registerButton() {
        btnImplicit.setOnClickListener(this)
        btnExpplicit.setOnClickListener(this)
        btnPhoneDiler.setOnClickListener(this)
        btnShowSh.setOnClickListener(this)
        btnstoreSh.setOnClickListener(this)
        btnclear.setOnClickListener(this)
    }

    fun buttonExplicit() {
        val intent = Intent(this, Activity2::class.java)
        val data: String = ed1.getText().toString()
        if (data.equals("")) {
            ed1.setError("please Enter data")
        } else {
            intent.putExtra("data", data)
            startActivity(intent)
            ed1.setText("")
        }


    }

    fun buttonImplicit() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        // String shareBody = edmain.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, "Share wasim Text") //pass text here
        startActivity(Intent.createChooser(intent, "Share via"))
    }

    fun buttonPhone() {
        // edmain.setInputType(InputType.TYPE_CLASS_NUMBER);
        val intent = Intent(Intent.ACTION_DIAL)
        val number = ed1.getText().toString().trim()
        if (number == "") {
            ed1.setError("please Enter phone number")
        } else {
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
            ed1.setText("")
        }
    }

    fun storeSharedPrefrenceDAta() {
        val value: String = ed1.text.toString()
        if (value == "") {
            ed1.setError("please enter some data")
        } else {
            val sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("value", value)
            editor.apply()
            Toast.makeText(this, "Data saved in share PRefrences", Toast.LENGTH_SHORT).show()
        }
    }

    fun showSharedPrefrenceDAta() {
        val sh = getSharedPreferences("Data", MODE_PRIVATE)
        val value = sh.getString("value", "if no vaule show default one")
        tvShowShared.setText(value)
    }

    fun buttonClear() {
        val shp = getSharedPreferences("Data", MODE_PRIVATE)
        val editor = shp.edit()
        editor.clear()
        editor.apply()
        tvShowShared.setText("")
        Toast.makeText(this, "Shered Prefrences cleared", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                R.id.btnExpplicit -> buttonExplicit()
                R.id.btnImplicit -> buttonImplicit()
                R.id.btnPhoneDiler -> buttonPhone()
                R.id.btnstoreSh -> {
                    storeSharedPrefrenceDAta()
                    ed1.setText("")
                }
                R.id.btnShowSh -> showSharedPrefrenceDAta()
                R.id.btnclear -> buttonClear()
            }
        }
    }

}