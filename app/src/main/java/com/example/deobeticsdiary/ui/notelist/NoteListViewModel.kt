package com.example.deobeticsdiary.ui.notelist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.deobeticsdiary.data.dao.NoteDao
import com.example.deobeticsdiary.data.entity.Note
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NoteListViewModel  @ViewModelInject constructor(
    private val noteDao: NoteDao
) : ViewModel() {

    val list = noteDao.getAllNotes().asLiveData()

    private val eventChannel = Channel<Event>()
    val event = eventChannel.receiveAsFlow()

    sealed class Event {
        object NavigateToAddNote : Event()
        data class NavigateToEditNote(val note: Note) : Event()
    }
}