package com.flowerhop.kotlinandroidprac

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_todo.*

class AddTodoFragment: Fragment(R.layout.fragment_add_todo) {
    private val args by navArgs<AddTodoFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoViewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)
        editText.setText(args.memo)
        editText.setSelection(args.memo.length)
        btnSubmit.setOnClickListener {
            val text = editText.text
            if (text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Please insert text.", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            todoViewModel.addTodoIntent.postValue(text.toString())
            findNavController().popBackStack()
        }
    }
}