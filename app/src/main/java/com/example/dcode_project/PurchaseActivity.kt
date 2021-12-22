package com.example.dcode_project

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_purchase.*

class PurchaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        val intent2 = Intent()
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val desc = intent.getStringExtra("description")
        var remainder = intent.getStringExtra("RBALANCE")?.toInt()
        val room = arrayOf(name.toString(),price.toString(),desc.toString())

        orderTitle.text = name
        orderPrice.text = price
        totalPrice.text = price

        val orderBtn: Button = findViewById(R.id.placeOrderButton)
        orderBtn.setOnClickListener{
            if (price != null) {
                if (price.toInt() <= remainder!!) {
                    Toast.makeText(
                        this,
                        "Your order has been successfully placed.",
                        Toast.LENGTH_LONG
                    ).show()
                    orderBtn.setBackgroundDrawable(getDrawable(R.drawable.button))
                    orderBtn.setText("Order Placed!")
                    orderBtn.setTextColor(resources.getColor(R.color.white))

                    remainder -= price.toInt()
                    intent2.putExtra("RMB",remainder.toString())
                    intent2.putExtra("BOUGHT",room)
                }else{
                    Toast.makeText(
                        this,
                        "Insufficient funds. Please Top Up to Continue.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        setResult(Activity.RESULT_OK, intent2)
    }
}