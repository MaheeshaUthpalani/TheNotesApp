//package com.example.thenotesapp.fragments
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.Menu
//import android.view.MenuInflater
//import android.view.MenuItem
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.view.MenuHost
//import androidx.core.view.MenuProvider
//import androidx.lifecycle.Lifecycle
//import androidx.navigation.findNavController
//import com.example.thenotesapp.MainActivity
//import com.example.thenotesapp.R
//import com.example.thenotesapp.databinding.FragmentAddNoteBinding
//import com.example.thenotesapp.model.Note
//import com.example.thenotesapp.viewmodel.NoteViewModel
//
//class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {
//
//    private var addNoteBinding: FragmentAddNoteBinding? = null
//    private val binding get() = addNoteBinding!!
//
//    private lateinit var notesViewModel: NoteViewModel
//    private lateinit var addNoteView: View
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val menuHost: MenuHost = requireActivity()
//        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
//
//        notesViewModel = (activity as MainActivity).noteViewModel
//        addNoteView = view
//    }
//
//    private fun saveNote() {
//        val noteTitle = binding.addNoteTitle.text.toString().trim()
//        val noteDesc = binding.addNoteDesc.text.toString().trim()
//        val noteDate = binding.addNoteDate.text.toString().trim()
//        val noteTime = binding.addNoteTime.text.toString().trim()
//
//        if (noteTitle.isNotEmpty()) {
//            val note = Note(0, noteTitle, noteDate, noteTime, noteDesc)
//            notesViewModel.addNote(note)
//
//            Toast.makeText(addNoteView.context, "Note Saved", Toast.LENGTH_SHORT).show()
//            addNoteView.findNavController().popBackStack(R.id.homeFragment, false)
//        } else {
//            Toast.makeText(addNoteView.context, "Please enter note title", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//        menu.clear()
//        menuInflater.inflate(R.menu.menu_add_note, menu)
//    }
//
//    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//        return when (menuItem.itemId) {
//            R.id.saveMenu -> {
//                saveNote()
//                true
//            }
//            else -> false
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        addNoteBinding = null
//    }
//}

package com.example.thenotesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.thenotesapp.MainActivity
import com.example.thenotesapp.R
import com.example.thenotesapp.databinding.FragmentAddNoteBinding
import com.example.thenotesapp.model.Note
import com.example.thenotesapp.viewmodel.NoteViewModel

class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {

    // Use nullable binding
    private var addNoteBinding: FragmentAddNoteBinding? = null
    private val binding get() = addNoteBinding!!

    // View model for notes
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var addNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // Initialize view model
        notesViewModel = (activity as MainActivity).noteViewModel
        addNoteView = view

        // Set click listener on the new save button
        binding.saveNoteButton.setOnClickListener {
            saveNote()
        }
    }

    // Method to save the note data
    private fun saveNote() {
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()
        val noteDate = binding.addNoteDate.text.toString().trim()
        val noteTime = binding.addNoteTime.text.toString().trim()

        // Validate title before saving the note
        if (noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteDate, noteTime, noteDesc)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context, "Note Saved", Toast.LENGTH_SHORT).show()
            addNoteView.findNavController().popBackStack(R.id.homeFragment, false)
        } else {
            Toast.makeText(addNoteView.context, "Please enter note title", Toast.LENGTH_SHORT).show()
        }
    }

    // Inflate the menu for the fragment
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    // Handle menu item selection
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.saveMenu -> {
                saveNote()
                true
            }
            else -> false
        }
    }

    // Clear binding when fragment is destroyed
    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }
}
