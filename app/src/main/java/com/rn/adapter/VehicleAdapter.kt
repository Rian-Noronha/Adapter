package com.rn.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class VehicleAdapter(
    private val ctx:Context,
    private val vehicle:List<Vehicle>
):BaseAdapter(){
    private val logos:TypedArray by lazy {
        ctx.resources.obtainTypedArray(R.array.logos)
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}