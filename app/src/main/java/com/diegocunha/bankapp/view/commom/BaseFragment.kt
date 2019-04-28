package com.diegocunha.bankapp.view.commom

import android.app.AlertDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.diegocunha.bankapp.R
import com.diegocunha.bankapp.model.data.Error

open class BaseFragment : Fragment() {

    fun hideKeyboard() {
        val mgr = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(activity?.window?.decorView?.windowToken, 0)
    }

    fun showErrorMessage(error: Error) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.dialog_error_title))
            .setMessage(error.message)
            .setPositiveButton(getString(R.string.dialog_continue)) { dialog, which ->
                dialog.dismiss()
            }.show()
    }
}