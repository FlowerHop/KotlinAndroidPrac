package com.flowerhop.kotlinandroidprac

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flowerhop.kotlinandroidprac.database.AppDatabase
import com.flowerhop.kotlinandroidprac.mvvm.AnyViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_todo.*

class AddTodoFragment: Fragment(R.layout.fragment_add_todo) {
    private val args by navArgs<AddTodoFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoItemDB = AppDatabase.getInstance(requireContext().applicationContext)
        val todoItemRepository = TodoItemRepository(todoItemDB)
        val todoViewModelFactory = AnyViewModelFactory {
            TodoViewModel(todoItemRepository)
        }
        val todoViewModel = ViewModelProvider(requireActivity(), todoViewModelFactory).get(TodoViewModel::class.java)
        editText.setText(args.memo)
        editText.setSelection(args.memo!!.length)
        btnSubmit.setOnClickListener {
            val text = editText.text
            if (text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Please insert text.", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            todoViewModel.createNewTodo(text.toString())
            findNavController().popBackStack()
        }
    }
}