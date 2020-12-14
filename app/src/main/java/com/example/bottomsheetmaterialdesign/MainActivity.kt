package com.example.bottomsheetmaterialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ExampleBottomSheetDialogFragment.BottomSheetListener {



    lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    lateinit var toggleButton: ToggleButton
    lateinit var tv: TextView

    private lateinit var job: Job

    private lateinit  var uiScope: CoroutineScope





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToggleButton()

        modelBottomSheet()

        persistentBottomSheet()

        initViews()

        //model takes time, so notice the user while its being
    }

    fun persistentBottomSheet(){
        val bottomSheetLayout = findViewById<LinearLayout>(R.id.bottom_sheet_layout)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)

    }


    fun initToggleButton(){
        toggleButton = findViewById(R.id.toggleButton)

        toggleButton.setOnClickListener {
            if (toggleButton.isChecked){
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }else{
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    fun modelBottomSheet(){


        //val bottomSheetDialog = BottomSheetDialog(this)

        //val view = layoutInflater.inflate(R.layout.bottom_sheet, null)

        //bottomSheetDialog.setContentView(view)

        val frameLayout = findViewById<FrameLayout>(R.id.frame_layout)
        val modelBtn = findViewById<Button>(R.id.model_btn)

        //luego, probar sin coroutine
        modelBtn.setOnClickListener{
            job = Job()
            uiScope =CoroutineScope(Dispatchers.Main +  job)
            uiScope.launch {
                val bottomSheet = ExampleBottomSheetDialogFragment()
                //bottomSheetDialog.show() //put this inside a onClickListener
                bottomSheet.show(supportFragmentManager, "ExampleBottomSheet")
                job.cancel()
            }

        }
    }

    fun initViews (){
        tv = findViewById(R.id.item_clicked_tv)

        initToggleButton()

        initBottomSheetOnClickListeners()


    }

    fun initBottomSheetOnClickListeners(){
        findViewById<LinearLayout>(R.id.share_option).setOnClickListener {
            tv.text = "Share"

        }
        findViewById<LinearLayout>(R.id.skip_next_option).setOnClickListener {
            tv.text = "Skip next"
        }
        findViewById<LinearLayout>(R.id.internet_option).setOnClickListener {
            tv.text = "Internet"
        }
    }

    fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onButtonClicked(text: String) {
        tv.text = text
    }


}