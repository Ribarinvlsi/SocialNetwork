package com.example.oop_33

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.oop_33.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val changeInfoActivityRequestCode = 1
    private val VALID_LOGIN = "user"
    private val VALID_PASSWORD = "1234"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener {
            if (validateForm(VALID_LOGIN, VALID_PASSWORD)) {
                binding.lLogin.visibility = View.GONE
            }
        }
        binding.btEditProfile.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, changeInfoActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == changeInfoActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra("name")
            val surname = data?.getStringExtra("surname")
            val imageUri = data?.getStringExtra("imageUri")
            if (name != null) { binding.tvName.text = name }
            if (surname != null) { binding.tvSurname.text = surname }
            if (imageUri != null) { binding.ivAvatar.setImageURI(Uri.parse(imageUri)) }
        }
    }

    private fun validateForm(correctLogin: String, correctPassword: String): Boolean {
        var isValid = true
        if (binding.etLogin.text.toString().isEmpty()) {
            binding.etLogin.error = "Please enter a login"
            isValid = false
        } else if (binding.etLogin.text.toString() != correctLogin) {
            binding.etLogin.error = "Incorrect login"
            isValid = false
        }
        if (binding.etPassword.text.toString().isEmpty()) {
            binding.etPassword.error = "Please enter a password"
            isValid = false
        } else if (binding.etPassword.text.toString() != correctPassword) {
            binding.etPassword.error = "Incorrect password"
            isValid = false
        }
        return isValid
    }
}