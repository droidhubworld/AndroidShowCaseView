package com.droidhubworld.library.calback

import com.droidhubworld.library.ShowCase

interface ShowCaseListener {
    /**
     * It is called when ShowCase Show
     */
    fun onShowCaseShow(showCase: ShowCase)

    /**
     * It is called when the user clicks on the targetView
     */
    fun onShowCaseTargetClick(showCase: ShowCase)

    /**
     * It is called when the user clicks on the close icon
     */
    fun onShowCaseCloseActionClick(showCase: ShowCase,skip: Boolean=false)

    /**
     * It is called when the user clicks on the background dim
     */
    fun onShowCaseBackgroundDimClick(showCase: ShowCase)

    /**
     * It is called when the user clicks on the bubble
     */
    fun onShowCaseClick(showCase: ShowCase)
}