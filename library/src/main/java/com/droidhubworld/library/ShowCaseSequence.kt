package com.droidhubworld.library

import com.droidhubworld.library.calback.ShowCaseSequenceListener
import kotlin.reflect.KClass

class ShowCaseSequence {
    private val mShowCaseBuilderList = ArrayList<ShowCaseBuilder>()

    init {
        mShowCaseBuilderList.clear()
    }

    fun addShowCase(showCaseBuilder: ShowCaseBuilder): ShowCaseSequence {
        mShowCaseBuilderList.add(showCaseBuilder)
        return this
    }

    fun addShowCases(showCaseBuilderList: List<ShowCaseBuilder>): ShowCaseSequence {
        mShowCaseBuilderList.addAll(showCaseBuilderList)
        return this
    }

    fun show() = show(0)

    private fun show(position: Int) {
        if (position >= mShowCaseBuilderList.size)
            return

        when (position) {
            0 -> {
                mShowCaseBuilderList[position].isFirstOfSequence(true)
                mShowCaseBuilderList[position].isLastOfSequence(false)
            }

            mShowCaseBuilderList.size - 1 -> {
                mShowCaseBuilderList[position].isFirstOfSequence(false)
                mShowCaseBuilderList[position].isLastOfSequence(true)
            }

            else -> {
                mShowCaseBuilderList[position].isFirstOfSequence(false)
                mShowCaseBuilderList[position].isLastOfSequence(false)
            }
        }

        mShowCaseBuilderList[position].sequenceListener(object : ShowCaseSequenceListener {
            override fun onDismiss(skip: Boolean) {
                if (skip) {
                    show(mShowCaseBuilderList.size)
                } else {
                    show(position + 1)
                }
            }
        }).show()
    }
}