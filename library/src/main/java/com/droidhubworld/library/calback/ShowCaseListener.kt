package com.droidhubworld.library.calback

import com.droidhubworld.library.ShowCase

interface ShowCaseListener {
    /**
     * It is called when the user clicks on the targetView
     */
    fun onTargetClick(showCase: ShowCase)

    /**
     * It is called when the user clicks on the close icon
     */
    fun onCloseActionImageClick(showCase: ShowCase)

    /**
     * It is called when the user clicks on the background dim
     */
    fun onBackgroundDimClick(showCase: ShowCase)

    /**
     * It is called when the user clicks on the bubble
     */
    fun onShowCaseClick(showCase: ShowCase)
}