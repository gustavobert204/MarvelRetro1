package com.example.marvelretro1.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog


object AlertUtils {

    fun showErrorAlert(context: Context, title: String = "Error", message: String): AlertDialog {
        return AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message.ifEmpty { "An error occurred. Please try again later" })
            setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        }.show()
    }
}