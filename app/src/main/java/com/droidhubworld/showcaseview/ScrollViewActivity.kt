package com.droidhubworld.showcaseview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.droidhubworld.library.ShowCase
import com.droidhubworld.library.ShowCaseBuilder
import com.droidhubworld.library.ShowCaseSequence
import com.droidhubworld.showcaseview.databinding.ActivityScrollViewBinding

class ScrollViewActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityScrollViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityScrollViewBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(mBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mBinding.btnOpenShowCase.setOnClickListener {
            getSequenceShowCase().show()
        }
        mBinding.btnNext.setOnClickListener {
            getSequenceShowCase().show()
        }
    }


    private fun getSequenceShowCase(): ShowCaseSequence {
        val viewList = listOf(
            /*ShowCaseData(
                view = mBinding.etYourName,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.etBusinessName,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.etAddress,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.etPhone,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.etEmail,
                title = "Welcome!!!",
                description = "This is a Example"
            ),*/
            ShowCaseData(
                view = mBinding.tvWeekdaysFrom,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.tvWeekdaysTo,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.tvWeekendsFrom,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.tvWeekendsTo,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.btnBack,
                title = "Welcome!!!",
                description = "This is a Example"
            ),
            ShowCaseData(
                view = mBinding.btnNext,
                title = "Welcome!!!",
                description = "This is a Example"
            ),

            )
        val showCaseList = mutableListOf<ShowCaseBuilder>()
        viewList.forEach {
            showCaseList.add(getSimpleShowCaseBuilder(it))
        }
        return ShowCaseSequence().addShowCases(showCaseList)
    }

    private fun getSimpleShowCaseBuilder(
        showCaseData: ShowCaseData
    ): ShowCaseBuilder {
        return ShowCaseBuilder(this)
            .title(showCaseData.title)
            .description(showCaseData.description)
            .backgroundColor(ContextCompat.getColor(this, R.color.teal_700))
            .arrowPosition(showCaseData.arrowPosition)
            .showButtons(true)
            .targetView(showCaseData.view)
    }
}