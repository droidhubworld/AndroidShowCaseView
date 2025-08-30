package com.droidhubworld.showcaseview

import android.view.View
import com.droidhubworld.library.ShowCase

data class ShowCaseData(
    val view: View,
    val title: String,
    val description: String,
    val arrowPosition: ShowCase.ArrowPosition = ShowCase.ArrowPosition.BOTTOM,
)
