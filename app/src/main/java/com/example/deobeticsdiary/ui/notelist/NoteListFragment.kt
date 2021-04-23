package com.example.deobeticsdiary.ui.notelist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.deobeticsdiary.R
import com.example.deobeticsdiary.data.entity.Note
import com.example.deobeticsdiary.databinding.NoteListFragmentBinding
import com.example.deobeticsdiary.util.NpaLinerLayoutManager
import com.example.deobeticsdiary.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class NoteListFragment: Fragment(R.layout.note_list_fragment), NoteListAdapter.OnItemClickListener{

    private val viewModel: NoteListViewModel by viewModels()

    private val listAdapter: NoteListAdapter = NoteListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = NoteListFragmentBinding.bind(view)

        binding.apply {
            noteList.apply {
                adapter = listAdapter
                layoutManager = NpaLinerLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.list.observe(viewLifecycleOwner) {
            listAdapter.setData(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.event.collect { event ->
                when (event) {
                    NoteListViewModel.Event.NavigateToAddNote -> {

                    }
                    is NoteListViewModel.Event.NavigateToEditNote -> {

                    }
                }.exhaustive
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_list_menu, menu)
    }

    override fun onItemClick(note: Note) {

    }
}