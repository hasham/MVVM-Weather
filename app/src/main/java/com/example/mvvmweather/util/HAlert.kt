package com.example.mvvmweather.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.mvvmweather.R
import com.google.android.material.snackbar.Snackbar

/**
 * Developed by Hasham.Tahir on 1/16/2017.
 */

object HAlert {

    fun showAlertDialog(context: Activity, title: String, message: String) {

        val alert = AlertDialog.Builder(context)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        alert.show()
    }

    fun showAlertDialog(context: FragmentActivity, title: String, message: String) {

        val alert = AlertDialog.Builder(context)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        alert.show()
    }

    fun showAlertDialog(context: Activity, title: String, message: String, listener: DialogInterface.OnClickListener) {

        val alert = AlertDialog.Builder(context)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.setPositiveButton("OK", listener)
        alert.setCancelable(false)
        alert.show()
    }

    fun showSnackBar(view: View, msg: String) {
        //        findViewById(android.R.id.content) item_spinner_hint
        try {
            val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
            val snackBarView = snackbar.view
            val tv = snackBarView.findViewById<View>(R.id.snackbar_text) as TextView
            tv.setTextColor(Color.WHITE)
            snackbar.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

//    fun showFlashBar(activity: Activity, message: String, colorRes: Int) {
//
//        Flashbar.Builder(activity)
//                .gravity(Flashbar.Gravity.BOTTOM)
////                .title("Hello World!")
//                .message(message)
//                .backgroundColorRes(colorRes)
//                .build()
//    }

    fun showToast(context: Context, msg: String) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: Activity, msg: String) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: FragmentActivity, msg: String) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: Activity, msg: String, length: Int) {

        Toast.makeText(context, msg, length).show()
    }
}
