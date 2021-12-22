package com.example.dcode_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dcode_project.Fragment.TopFragment
import com.example.dcode_project.data_class.product
import kotlinx.android.synthetic.main.product_grid.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val finallist = intent.getStringArrayListExtra("ITEMLIST") as List<Array<String>>
        var r = intent.getStringExtra("R")

        productHistoryRecyclerView.layoutManager = LinearLayoutManager(this)
        productHistoryRecyclerView.adapter = boughtlistadapter(finallist)
        productHistoryRecyclerView.layoutManager = GridLayoutManager(this, 2)

        //Wallet Intent
        val walletButton: ImageButton = findViewById(R.id.walletButton)
        walletButton.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            intent.putExtra("TOTALBALANCE",r)
            startActivity(intent)
        }

        val top_fragment = TopFragment()
        supportFragmentManager.beginTransaction().replace(R.id.topContainerView, top_fragment)
            .commit()
    }

    private inner class BoughtHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = itemView.findViewById(R.id.productName)
        var description: TextView = itemView.findViewById(R.id.productDescription)
        var price: TextView = itemView.findViewById(R.id.productPrice)
    }

    private inner class boughtlistadapter(var bought: List<Array<String>>) :
        RecyclerView.Adapter<BoughtHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoughtHolder {
            val view = layoutInflater.inflate(R.layout.history_grid, parent, false)
            return BoughtHolder(view)
        }

        override fun onBindViewHolder(holder: BoughtHolder, position: Int) {
            val purchased = bought[position]
            holder.apply {
                name.text = purchased[0]
                description.text = purchased[2]
                price.text = purchased[1]
            }
        }

        override fun getItemCount(): Int {
            return bought.size
        }
    }
}