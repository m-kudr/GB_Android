package com.example.app18.presentation.viewmodels

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app18.data.model.Photo
import com.example.app18.databinding.ItemPhotoBinding


class DiffUtilCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}

class ListPhotoAdapter(
    private val onClick: (Photo) -> Unit
) : ListAdapter<Photo, ListPhotoViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPhotoViewHolder {
        return ListPhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListPhotoViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            date.text = "Дата : ${item.date}"
            Glide.with(image.context)
                .load(item.uri)
                .into(image)
        }

        holder.binding.root.apply {
            this.setOnClickListener {
                onClick(item)
            }
        }
    }
}

class ListPhotoViewHolder(
    val binding: ItemPhotoBinding
) : RecyclerView.ViewHolder(binding.root)
