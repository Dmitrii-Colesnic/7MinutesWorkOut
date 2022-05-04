package com.example.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(var items: ArrayList<ExerciseModel>) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root) {
        val item = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //for every single item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //var model = items[position]
        //holder.item.text = model.getId().toString()

        when{
            items[position].getIsSelected() -> {

                holder.item.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_selected_circular_color_yellow_background
                )

            } else -> {

            holder.item.background = ContextCompat.getDrawable(
                holder.itemView.context,
                R.drawable.item_circular_color_yellow_background
            )

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}