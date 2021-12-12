package com.example.project3.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.project3.R
import com.example.project3.data.AppDatabase
import java.lang.IllegalStateException

class DBClearDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_yes
                ) { _, _ ->
                    val dao = AppDatabase.db.quizDao
                    dao.deleteAllData()
                }
                .setNegativeButton(R.string.dialog_no) { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}