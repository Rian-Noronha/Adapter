package com.rn.adapter


import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.rn.adapter.databinding.ItemVehicleBinding


class VehicleAdapter(
    private val ctx:Context,
    private val vehicles:List<Vehicle>
):BaseAdapter(){
    private val logos:TypedArray by lazy {
        ctx.resources.obtainTypedArray(R.array.logos)
    }

    override fun getCount(): Int = vehicles.size

    override fun getItem(position: Int) = vehicles[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemVehicleBinding
        val vehicle = vehicles[position]

        if(convertView == null){
            binding = ItemVehicleBinding.inflate(LayoutInflater.from(ctx), parent, false)
        }else{
            binding = ItemVehicleBinding.bind(convertView)
        }

        val holder = ViewHolder(binding)

        holder.imgLogo.setImageDrawable(logos.getDrawable(vehicle.manufacturer))
        holder.txtModel.text = vehicle.model
        holder.txtYear.text = vehicle.year.toString()
        holder.txtFuel.text = ctx.getString(getFuel(vehicle))

        return binding.root
    }

    companion object{
        data class ViewHolder(val binding: ItemVehicleBinding){
            val imgLogo: ImageView = binding.imgLogo
            val txtModel: TextView = binding.txtModel
            val txtYear: TextView = binding.txtYear
            val txtFuel: TextView = binding.txtFuel
        }
    }

    @StringRes
    private fun getFuel(vehicle: Vehicle): Int =
        if(vehicle.gasoline && vehicle.ethanol) R.string.fuel_flex
        else if (vehicle.gasoline) R.string.fuel_gasoline
        else if (vehicle.ethanol) R.string.fuel_ethanol
        else R.string.fuel_invalid

}