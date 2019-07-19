package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender


class MainActivity : AppCompatActivity(), View.OnClickListener, TextView.OnEditorActionListener {
    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity","onCreate")
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send
        sendBtn.setOnClickListener(this)




        val status = savedInstanceState?.getString("STATUS")?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION")?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val(r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)
        benderImage.setOnClickListener{
            this@MainActivity.hideKeyboard()
        }
        messageEt.setOnEditorActionListener(this)

        textTxt.text = benderObj.askQuestion()
        Log.d("M_MainActivity",benderObj.askQuestion())

    }

    override fun onEditorAction(p0: TextView?, id: Int, p2: KeyEvent?): Boolean
    {
        if (id == EditorInfo.IME_ACTION_DONE)
        {
            sendMessage()
        }
        return false
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity","onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","onDestroy")
    }

    override fun onClick(v: View?) {
        if(v?.id==R.id.iv_send){
            sendMessage()
        }
    }
    private fun sendMessage()
    {
        this.hideKeyboard()
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
        messageEt.setText("")
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS",benderObj.status.name)
        outState?.putString("QUESTION",benderObj.question.name)
        Log.d("M_MainActivity","onSavedInstanceState123")
        Log.d("M_MainActivity","onSavedInstanceState ${benderObj.status.name} ${benderObj.question.name}")
    }
}
