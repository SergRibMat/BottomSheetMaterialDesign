package com.example.bottomsheetmaterialdesign

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.ClassCastException

class ExampleBottomSheetDialogFragment : BottomSheetDialogFragment() {
    lateinit var bottomSheetListener: BottomSheetListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.bottom_sheet, container, false)

        initViews(v)

        return v
    }


    public interface BottomSheetListener{
        fun onButtonClicked(text: String)
    }

    fun initViews(v: View){
        v.findViewById<LinearLayout>(R.id.share_option).setOnClickListener {
            bottomSheetListener.onButtonClicked("Share")
            dismiss()
        }
        v.findViewById<LinearLayout>(R.id.skip_next_option).setOnClickListener {
            bottomSheetListener.onButtonClicked("Skip next")
            dismiss()
        }
        v.findViewById<LinearLayout>(R.id.internet_option).setOnClickListener {
            bottomSheetListener.onButtonClicked("Internet")
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            bottomSheetListener = context as BottomSheetListener
        }catch (e: ClassCastException){
            throw ClassCastException("$context must implement BottomSheetListener")
        }
    }
}