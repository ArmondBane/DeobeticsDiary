package com.example.deobeticsdiary.ui.notelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deobeticsdiary.R
import com.example.deobeticsdiary.data.entity.Note
import com.example.deobeticsdiary.databinding.NoteListItemFragmentBinding

class NoteListAdapter(private val listener: OnItemClickListener)
    : ListAdapter<Note, NoteListAdapter.ViewHolder>(DiffCallback()) {

    private var list: List<Note> = ArrayList()

    fun setData(list: List<Note>) {
        this.list = list
        submitList(this.list)
    }

    interface OnItemClickListener{
        fun onItemClick(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteListItemFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: NoteListItemFragmentBinding)
        : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(list[position])
                    }
                }
            }
        }
        fun bind(note: Note) = with(itemView) {
            binding.apply {
                sugarLevel.text = resources.getString(
                    R.string.sugar_level,
                    note.sugarLevel.toString()
                )
                insulinTaken.text = resources.getString(
                    R.string.insulin_taken,
                    note.insulinTaken.toString()
                )
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Note, newItem: Note) =
            oldItem == newItem
    }
}