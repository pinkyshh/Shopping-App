package com.example.dcode_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_purchase.*
import kotlinx.android.synthetic.main.activity_wallet.*

class WalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        //Balance Saved State
        val intent2 = Intent()
        val balancefrommain = intent.getStringExtra("TOTALBALANCE")
        balance.setText(balancefrommain)
        var AVBalance = balance.text

        var balanceInt = 0
        var amount = balancefrommain?.toInt()

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.Button1000) {
                //Calculation
                Button1000.setOnClickListener {
                    Button1000.setBackgroundDrawable(getDrawable(R.drawable.box_yellow))
                    Button2000.setBackgroundDrawable(getDrawable(R.drawable.box_grey))
                    Button5000.setBackgroundDrawable(getDrawable(R.drawable.box_grey))

                    confirm_button.setOnClickListener {
                        Toast.makeText(
                            this,
                            "Top Up Complete",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (amount != null) {
                            balanceInt = Button1000.text.substring(1, 5).toInt()
                            amount += balanceInt
                            balance.setText(amount.toString())
                            AVBalance = amount.toString()
                            intent2.putExtra("BALANCE", AVBalance)
                        }
                    }
                }
            } else if (checkedId == R.id.Button2000) {
                Button2000.setOnClickListener {
                    Button1000.setBackgroundDrawable(getDrawable(R.drawable.box_grey))
                    Button2000.setBackgroundDrawable(getDrawable(R.drawable.box_yellow))
                    Button5000.setBackgroundDrawable(getDrawable(R.drawable.box_grey))

                    confirm_button.setOnClickListener {
                        Toast.makeText(
                            this,
                            "Top Up Complete",
                            Toast.LENGTH_SHORT
                        ).show()

                        if (amount != null) {
                            balanceInt = Button2000.text.substring(1, 5).toInt()
                            amount += balanceInt
                            balance.setText(amount.toString())
                            AVBalance = amount.toString()
                            intent2.putExtra("BALANCE", AVBalance)
                        }
                    }
                }
            } else {
                Button5000.setOnClickListener {
                    Button1000.setBackgroundDrawable(getDrawable(R.drawable.box_grey))
                    Button2000.setBackgroundDrawable(getDrawable(R.drawable.box_grey))
                    Button5000.setBackgroundDrawable(getDrawable(R.drawable.box_yellow))
                    confirm_button.setOnClickListener {
                        Toast.makeText(
                            this,
                            "Top Up Complete",
                            Toast.LENGTH_SHORT
                        ).show()

                        if (amount != null) {
                            balanceInt = Button5000.text.substring(1, 5).toInt()
                            amount += balanceInt
                            balance.setText(amount.toString())
                            AVBalance = amount.toString()
                            intent2.putExtra("BALANCE", AVBalance)
                        }
                    }
                }
            }
            setResult(Activity.RESULT_OK, intent2)
        }
    }
}