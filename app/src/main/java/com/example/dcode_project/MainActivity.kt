package com.example.dcode_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dcode_project.Fragment.TopFragment
import com.example.dcode_project.data_class.product
import kotlinx.android.synthetic.main.product_grid.*
import kotlinx.android.synthetic.main.product_grid.view.*

private const val REQUESTCODE=102
private const val REQUEST_CODE_SECONDACT=101

class MainActivity : AppCompatActivity() {
var boughtitemstore = mutableListOf<Array<String>>()
var remainingBalance:String = "0"

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val top_fragment = TopFragment()
        supportFragmentManager.beginTransaction().replace(R.id.topContainerView, top_fragment)
            .commit()

        recyclerView = findViewById(R.id.productRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val products = productList()
        recyclerView.adapter=RecyclerViewAdapter(products)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        //Wallet Intent
        val walletButton: ImageButton = findViewById(R.id.walletButton)
        walletButton.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            intent.putExtra("TOTALBALANCE", remainingBalance)
            startActivityForResult(intent, REQUEST_CODE_SECONDACT)
        }

        //History Intent
        val historyButton: ImageButton = findViewById(R.id.historyButton)
        historyButton.setOnClickListener {
            val intent3 = Intent(this, HistoryActivity::class.java)
            intent3.putExtra("ITEMLIST", ArrayList(boughtitemstore))
            intent3.putExtra("R",remainingBalance)
            startActivity(intent3)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK){
            return
        }

        if (requestCode == REQUEST_CODE_SECONDACT){
            if(data != null){
                var rb = data.getStringExtra("BALANCE")
                if (rb != null) {
                    remainingBalance = rb
                }
                Log.d("BALANCECALL",remainingBalance)
            }
            else{
                Log.e("BALANCECALL","FAILEDBALANCE")
            }
        }
        if (requestCode == REQUESTCODE){
            if(data != null){
                data.getStringArrayExtra("BOUGHT")?.let { boughtitemstore.add(it) }
                var rb = data.getStringExtra("RMB")
                if (rb != null) {
                    remainingBalance = rb
                }
                Log.d("BALANCECALL",boughtitemstore.toString())
            }
            else{
                Log.e("BALANCECALL","FAILEDBALANCE")
            }
        }

    }

    private fun productList() : ArrayList<product> {
        val product = ArrayList<product>()
        product.add(product("VMS 0101", "Hall with 100 seating, projector and speakers",5000))
        product.add(product("VMS 0202", "Computer lab with 25 seating, projector and mic.",2000))
        product.add(product("VMS 0303", "Classroom with black board, projector and mic.", 1000))
        product.add(product("VMS 0404", "Activity room for open discussions.", 3000))

        return product
    }

    private inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var name: TextView = itemView.findViewById(R.id.productName)
        var description: TextView = itemView.findViewById((R.id.productDescription))
        var price: TextView = itemView.findViewById((R.id.productPrice))

        lateinit var product: product

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(product: product) {
            this.product = product
            name.text = (product.name)
            description.text = (product.description)
            price.text = (product.price.toString())
        }

        override fun onClick(v: View?) {
            itemView.buyProductButtonGrid.setOnClickListener{
                val intent= Intent(v!!.context,PurchaseActivity::class.java)
                intent.putExtra("name",product.name)
                intent.putExtra("description", product.description)
                intent.putExtra("price", product.price.toString())
                intent.putExtra("RBALANCE", remainingBalance)

                startActivityForResult(intent, REQUESTCODE)
            }
        }
    }

    private inner class RecyclerViewAdapter(var products: ArrayList<product>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
            val view=layoutInflater.inflate(R.layout.product_grid,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val product = products[position]
            (holder as ViewHolder).bind(product)
        }

        override fun getItemCount(): Int {
            return products.size
        }
    }
}