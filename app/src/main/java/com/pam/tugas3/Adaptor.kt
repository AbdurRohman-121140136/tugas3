package com.pam.tugas3.ui.skill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.pam.tugas3.databinding.SkillItemBinding
import com.pam.tugas3.R

class Adaptor(private var list:ArrayList<Skill>): RecyclerView.Adapter<Adaptor.MyView>() {

    var onItemClick: ((Skill) -> Unit)? = null

    fun setFilteredList(list: ArrayList<Skill>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
//        return MyView(SkillItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        val currentItem = list[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.heading
        holder.itemView.setOnClickListener{
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(currentItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: AppCompatImageView = itemView.findViewById(R.id.imageView)
        val tvHeading: TextView = itemView.findViewById(R.id.textView)
    }
}