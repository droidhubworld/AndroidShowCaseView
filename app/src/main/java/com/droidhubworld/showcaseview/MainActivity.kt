package com.droidhubworld.showcaseview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.droidhubworld.library.ShowCase
import com.droidhubworld.library.ShowCaseBuilder
import com.droidhubworld.library.ShowCaseSequence
import com.droidhubworld.library.calback.ShowCaseListener
import com.droidhubworld.showcaseview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonSimpleShowCase.setOnClickListener { getSimpleShowCaseBuilder().show() }
        binding.buttonColorShowCase.setOnClickListener { startActivity(Intent(this,
            ScrollViewActivity::class.java)) }
        binding.editText.setOnClickListener { getEditCustomColorShowCaseBuilder().show() }
        binding.buttonTextSizeShowCase.setOnClickListener { getTextSizeShowCaseBuilder().show() }
        binding.buttonArrowLeftShowCase.setOnClickListener { getArrowLeftShowCaseBuilder().show() }
        binding.buttonArrowRightShowCase.setOnClickListener { getArrowRightShowCaseBuilder().show() }
        binding.buttonEventListener.setOnClickListener { getListenerShowCaseBuilder().show() }
        binding.buttonSequence.setOnClickListener { getSequence().show() }
    }

    //SHOW CASES GETTERS

    private fun getSimpleShowCaseBuilder(): ShowCaseBuilder {
        return ShowCaseBuilder(this)
            .title("Welcome!!!")
            .description("This is a simple ShowCase with default values.")
            .targetView(binding.buttonSimpleShowCase)
    }

    private fun getCustomColorShowCaseBuilder(): ShowCaseBuilder {
        return ShowCaseBuilder(this)
            .title("Custom your show case style!")
            .description("It is possible to change the text color, background ... and you can even add an image into your show case.")
            .backgroundColor(ContextCompat.getColor(this, R.color.colorBlueGray))
            .image(ContextCompat.getDrawable(this, R.drawable.color_palette)!!)
            .closeActionImage(ContextCompat.getDrawable(this, R.drawable.ic_close)!!)
            .textColor(ContextCompat.getColor(this, R.color.colorBlack))
            .targetView(binding.buttonColorShowCase)
    }
    private fun getEditCustomColorShowCaseBuilder(): ShowCaseBuilder {
        return ShowCaseBuilder(this)
            .title("Custom your show case style!")
            .description("It is possible to change the text color, background ... and you can even add an image into your show case.")
            .backgroundColor(ContextCompat.getColor(this, R.color.colorBlueGray))
            .image(ContextCompat.getDrawable(this, R.drawable.color_palette)!!)
            .closeActionImage(ContextCompat.getDrawable(this, R.drawable.ic_close)!!)
            .textColor(ContextCompat.getColor(this, R.color.colorBlack))
            .targetView(binding.editText)
    }

    private fun getTextSizeShowCaseBuilder(): ShowCaseBuilder {
        return ShowCaseBuilder(this)
            .title("Change text sizes!")
            .description("You can also choose the best text size for you.")
            .backgroundColor(ContextCompat.getColor(this, R.color.colorTeal))
            .image(ContextCompat.getDrawable(this, R.drawable.baseline_text_fields_24)!!)
            .titleTextSize(18)
            .descriptionTextSize(16)
            .closeActionImage(null)
            .targetView(binding.buttonTextSizeShowCase)
    }

    private fun getArrowLeftShowCaseBuilder(): ShowCaseBuilder {
        return ShowCaseBuilder(this)
            .title("Force the position of the show case!")
            .description("You only have to specify in which side you want the arrow, and the show case will be located depending on it.")
            .arrowPosition(ShowCase.ArrowPosition.LEFT)
            .backgroundColor(ContextCompat.getColor(this, R.color.colorRed))
            .targetView(binding.buttonArrowLeftShowCase)
    }

    private fun getArrowRightShowCaseBuilder(): ShowCaseBuilder {
        return ShowCaseBuilder(this)
            .title("Arrow set on right side this time :)")
            .arrowPosition(ShowCase.ArrowPosition.RIGHT)
            .backgroundColor(ContextCompat.getColor(this, R.color.colorPink))
            .targetView(binding.buttonArrowRightShowCase)
    }


    private fun getListenerShowCaseBuilder(): ShowCaseBuilder {
        return ShowCaseBuilder(this)
            .title("Listen user actions!")
            .description("You can detect when the user interacts with the different view elements to act consequently.")
            .backgroundColor(ContextCompat.getColor(this, R.color.colorOrange))
            .image(ContextCompat.getDrawable(this, R.drawable.ic_smily)!!)
            .listener(object : ShowCaseListener {
                override fun onShowCaseClick(showCase: ShowCase) {
                    Toast.makeText(this@MainActivity, "OnClick", Toast.LENGTH_SHORT).show()
                }

                override fun onBackgroundDimClick(showCase: ShowCase) {
                    Toast.makeText(this@MainActivity, "OnBackgroundDimClick", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onTargetClick(showCase: ShowCase) {
                    Toast.makeText(this@MainActivity, "OnTargetClick", Toast.LENGTH_SHORT).show()
                }

                override fun onCloseActionImageClick(showCase: ShowCase) {
                    Toast.makeText(this@MainActivity, "OnClose", Toast.LENGTH_SHORT).show()
                }
            })
            .targetView(binding.buttonEventListener)
    }

    private fun getSequence(): ShowCaseSequence {
        return ShowCaseSequence().addShowCases(
            listOf(
                getSimpleShowCaseBuilder(),
                getCustomColorShowCaseBuilder(),
                getEditCustomColorShowCaseBuilder(),
                getTextSizeShowCaseBuilder(),
                getArrowLeftShowCaseBuilder(),
                getArrowRightShowCaseBuilder(),
                getListenerShowCaseBuilder()
            )
        )
    }
}