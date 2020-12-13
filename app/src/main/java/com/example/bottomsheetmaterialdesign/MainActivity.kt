package com.example.bottomsheetmaterialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.ToggleButton
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {



    lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    lateinit var toggleButton: ToggleButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        secondBottomSheet()

        firstBottomSheet()
    }

    fun firstBottomSheet(){
        val bottomSheetLayout = findViewById<LinearLayout>(R.id.bottom_sheet_layout)


        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)

        initToggleButton()

    }


    fun initToggleButton(){
        toggleButton = findViewById(R.id.toggleButton)

        toggleButton.setOnClickListener {
            if (toggleButton.isChecked){
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                showToast("CAMBIO A ON")
            }else{
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

                showToast("CAMBIO A OFF")
            }
        }
    }

    fun secondBottomSheet(){
        val bottomSheetDialog = BottomSheetDialog(this)

        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)

        bottomSheetDialog.setContentView(view)

        val frameLayout = findViewById<FrameLayout>(R.id.frame_layout)

        frameLayout.setOnClickListener{
            bottomSheetDialog.show() //put this inside a onClickListener
        }


    }

    fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


}