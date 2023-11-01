package com.rn.adapter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.rn.adapter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var txtFooter: TextView
    private lateinit var binding: ActivityMainBinding
    //0=VW, 1=GM, 2=FIAT, 3=FORD
    private val vehicles = mutableListOf(
        Vehicle("Onix", 2018, 1, true, true),
        Vehicle("Uno", 2007, 2, true, false),
        Vehicle("Del Rey", 1988, 3, false, true),
        Vehicle("Gol", 2014, 0, true, true)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listView.emptyView = binding.empty
        val adapter = VehicleAdapter(this, vehicles)
        binding.listView.adapter = adapter

        /*
        listView.setOnItemClickListener { parent, view, position, id ->
            val (model, year) = vehicles[position]
           Toast.makeText(this, "$model $year", Toast.LENGTH_SHORT).show()
        }*/
        initHeaderAndFooter(binding.listView, adapter)
        binding.listView.setOnItemClickListener{parent, _, position, _ ->

            val vehicle = parent.getItemAtPosition(position)as?
                    Vehicle
            if(vehicle!=null){
                val (model, year) = vehicle
                Toast.makeText(this, "$model $year", Toast.LENGTH_SHORT).show()
                vehicles.remove(vehicle)
                adapter.notifyDataSetChanged()
                txtFooter.text = resources.getQuantityString(R.plurals.footer_text, adapter.count, adapter.count)
            }

        }



    }

    private fun initHeaderAndFooter(listView:ListView, adapter:VehicleAdapter){

        val padding = 8
        val txtHeader = TextView(this)
        txtHeader.setBackgroundColor(Color.GRAY)
        txtHeader.setTextColor(Color.WHITE)
        txtHeader.setText(R.string.header_text)
        txtHeader.setPadding(padding, padding, 0, padding)
        listView.addHeaderView(txtHeader)

        txtFooter = TextView(this)
        txtFooter.text = resources.getQuantityString(
          R.plurals.footer_text, adapter.count, adapter.count)
        txtFooter.setBackgroundColor(Color.LTGRAY)
        txtFooter.gravity = Gravity.END
        txtFooter.setPadding(0, padding, padding, padding)
        listView.addFooterView(txtFooter)

    }
}