package com.example.ecommercesimpleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class uploadProductActivity : AppCompatActivity() {

    private lateinit var progressbar : ProgressBar
    private lateinit var productimagePreview : ImageView
    private lateinit var edtProductName : EditText
    private lateinit var edtProductPrice : EditText
    private lateinit var edtProductDesc : EditText
    private lateinit var btnUploadProduct : Button
    private lateinit var btnSelectProduct : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_product)

        productimagePreview = findViewById(R.id.imgProductpreview)
        edtProductName = findViewById(R.id.edtProductName)
        edtProductPrice = findViewById(R.id.edtProductPrice)
        edtProductDesc = findViewById(R.id.edtProductDesc)
        btnSelectProduct = findViewById(R.id.btnselectProduct)
        btnUploadProduct = findViewById(R.id.btnuploadProduct)
        progressbar = findViewById(R.id.progressbar)

        btnSelectProduct.setOnClickListener(){
            val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent,101)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK){
            var uri = data?.data
            productimagePreview.setImageURI(uri)

            btnUploadProduct.setOnClickListener(){
                //uploading image to firebase and getting link
                progressbar.visibility = View.VISIBLE
                var productName = edtProductName.text.toString()
                var productPrice = edtProductPrice.text.toString()
                var productDesc = edtProductDesc.text.toString()

                var filename = UUID.randomUUID().toString()+".jpg"
                var storageRef = FirebaseStorage.getInstance().getReference().child("productImages/$filename")
                storageRef.putFile(uri!!).addOnSuccessListener {
                    var result = it.metadata?.reference?.downloadUrl
                    result?.addOnSuccessListener {

                        uploadProduct(
                            productName,
                            productPrice,
                            productDesc,
                            it.toString()
                        )
                    }
                }
            }

        }
    }

    private fun uploadProduct(name : String, price : String, desc : String, link :String){
        Firebase.database.getReference("products").child(name).setValue(
            Product(
                ProductName = name,
                ProductPrice = price,
                ProductDesc = desc,
                ProductImage = link
        )
        ).addOnSuccessListener {
            progressbar.visibility = View.GONE
            Toast.makeText(this, "product uploaded successfully", Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this,MainActivity::class.java)
            )
        }
    }
}







