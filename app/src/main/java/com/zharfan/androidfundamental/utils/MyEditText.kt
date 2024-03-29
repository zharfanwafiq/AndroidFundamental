package com.zharfan.androidfundamental.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.zharfan.androidfundamental.R

class MyEditText: AppCompatEditText, View.OnTouchListener {

    private lateinit var mClearButtonImage:Drawable

    constructor(context: Context): super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) :super(context, attrs){
       init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :super(context, attrs, defStyleAttr){
        init()
    }

    private fun init(){
        mClearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_delete) as Drawable
        setOnTouchListener(this)

        addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               if(s.toString().isNotEmpty()){
                   showClearButton()
               }else{
                   hideClearButton()
               }
            }

            override fun afterTextChanged(s: Editable?) {
//                TODO("Not yet implemented")
            }

        })
    }

    private fun showClearButton(){
        setCompoundDrawablesWithIntrinsicBounds(null,null, mClearButtonImage,null)
    }
    private fun hideClearButton(){
        setCompoundDrawablesWithIntrinsicBounds(null,null, null, null)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (compoundDrawables[2] != null){
            val clearButtonStart: Float
            val  clearButtonEnd: Float
            var isClearButtonClicked = false
            when (layoutDirection){
                View.LAYOUT_DIRECTION_RTL -> {
                    clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                    when{
                        event!!.x < clearButtonEnd -> isClearButtonClicked = true
                    }
                }

                else -> {
                    clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                    when{
                        event!!.x > clearButtonStart -> isClearButtonClicked = true
                    }
                }
            }

            when {
                isClearButtonClicked -> when (event!!.action){
                    MotionEvent.ACTION_DOWN -> {
                        mClearButtonImage = ContextCompat.getDrawable(context,R.drawable.ic_delete) as Drawable
                        showClearButton()
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        mClearButtonImage = ContextCompat.getDrawable(context,R.drawable.ic_delete) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
                        return true
                    }
                    else -> return false
                }
                else -> return false
            }

        }
        return false
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        hint = "MAsukan Nama Anda"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
}