package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import android.R.attr.top
import android.R.attr.bottom
import android.graphics.Rect
import android.util.Log
import android.view.ViewTreeObserver





fun Activity.hideKeyboard(){
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.getCurrentFocus()
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
}
//fun Activity.isKeyboardOpen(){
//    val rootview = this.window.decorView
//    linearChatLayout.getViewTreeObserver().addOnGlobalLayoutListener(
//        ViewTreeObserver.OnGlobalLayoutListener {
//            val r = Rect()
//            rootview.getWindowVisibleDisplayFrame(r)
//            val screenHeight = rootview.rootView.height
//            val newHeight = screenHeight - (r.bottom - r.top)
//            if (newHeight > heightOfKeyboard) {
//                heightOfKeyboard = screenHeight - (r.bottom - r.top)
//                // heightOfKeyboard = heightDiff;
//            }
//
//            Log.d("Keyboard Size", "Size: $heightOfKeyboard")
//        })
//    return heightOfKeyboard
//}