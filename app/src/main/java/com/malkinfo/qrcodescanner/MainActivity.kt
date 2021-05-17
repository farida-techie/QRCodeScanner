package com.malkinfo.qrcodescanner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.malkinfo.qrcodescanner.view.ScannerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnScanner:Button
    private val REQUEST_CAMERA_PERMISSION = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**get Permission*/
        if (ContextCompat.checkSelfPermission(
                        this@MainActivity,Manifest.permission.CAMERA
                )!= PackageManager.PERMISSION_GRANTED){
            askCameraPermission()
        }else{
            setUpcontrollers()
        }


    }
    /**ok now run it*/

    private fun askCameraPermission() {
        ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(Manifest.permission.CAMERA),REQUEST_CAMERA_PERMISSION)
    }

    private fun setUpcontrollers() {
        /**set find Id*/
        btnScanner = findViewById(R.id.btnScanner)
        btnScanner.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                    ScannerActivity::class.java))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode ==REQUEST_CAMERA_PERMISSION && grantResults.isNotEmpty()){
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
                setUpcontrollers()
            }
            else{
                Toast.makeText(this@MainActivity,"Permission Denied",Toast.LENGTH_SHORT).show()
            }
        }
    }


}