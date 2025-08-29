package com.droidhubworld.library


import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import com.droidhubworld.library.calback.ShowCaseListener
import com.droidhubworld.library.calback.ShowCaseSequenceListener
import java.lang.ref.WeakReference
import java.util.ArrayList

class ShowCaseBuilder {
    internal var mActivity: WeakReference<Activity>? = null
    internal var mImage: Drawable? = null
    internal var mTitle: String? = null
    internal var mShowButtons: Boolean? = null
    internal var mSubtitle: String? = null
    internal var mCloseAction: Drawable? = null
    internal var mBackgroundColor: Int? = null
    internal var mTextColor: Int? = null
    internal var mTitleTextSize: Int? = null
    internal var mSubtitleTextSize: Int? = null
    internal var mHighlightMode: ShowCase.HighlightMode? = null
    internal var mDisableTargetClick: Boolean = false
    internal var mDisableCloseAction: Boolean = false
    internal var mShowOnce: String? = null
    internal var mIsFirstOfSequence: Boolean? = null
    internal var mIsLastOfSequence: Boolean? = null
    internal val mArrowPositionList = ArrayList<ShowCase.ArrowPosition>()
    internal var mTargetView: WeakReference<View>? = null
    internal var mShowCaseListener: ShowCaseListener? = null
    internal var mShowCaseSequenceListener: ShowCaseSequenceListener? = null

    private var onGlobalLayoutListenerTargetView: ViewTreeObserver.OnGlobalLayoutListener? = null

    /**
     * Builder constructor. It needs an instance of the current activity to convert it to a weak reference in order to avoid memory leaks
     */
    constructor(activity: Activity) {
        mActivity = WeakReference(activity)
    }

    /**
     * Title of the ShowCase. This text is bolded in the view.
     */
    fun title(title: String): ShowCaseBuilder {
        mTitle = title
        return this
    }
    /**
     * Show Buttons of the ShowCase. This i show and hide button.
     */
    fun showButtons(showButtons: Boolean): ShowCaseBuilder {
        mShowButtons = showButtons
        return this
    }

    /**
     * Additional description of the ShowCase. This text has a regular format
     */
    fun description(subtitle: String): ShowCaseBuilder {
        mSubtitle = subtitle
        return this
    }

    /**
     * Image drawable to inserted as main image in the ShowCase
     *  - If this param is not passed, the ShowCase will not have main image
     */
    fun image(image: Drawable): ShowCaseBuilder {
        mImage = image
        return this
    }

    /**
     * Image resource id to insert the corresponding drawable as main image in the ShowCase
     *  - If this param is not passed, the ShowCase will not have main image
     */
    fun imageResourceId(resId: Int): ShowCaseBuilder {
        mImage = ContextCompat.getDrawable(mActivity!!.get(), resId)
        return this
    }

    /**
     * Image drawable to be inserted as close icon in the ShowCase.
     *  - If this param is not defined, a default close icon is displayed
     */
    fun closeActionImage(image: Drawable?): ShowCaseBuilder {
        mCloseAction = image
        return this
    }

    /**
     * Image resource id to insert the corresponding drawable as close icon in the ShowCase.
     *  - If this param is not defined, a default close icon is displayed
     */
    fun closeActionImageResourceId(resId: Int): ShowCaseBuilder {
        mCloseAction = ContextCompat.getDrawable(mActivity!!.get(), resId)
        return this
    }


    /**
     * Background color of the ShowCase.
     *  - #3F51B5 color will be set if this param is not defined
     */
    fun backgroundColor(color: Int): ShowCaseBuilder {
        mBackgroundColor = color
        return this
    }

    /**
     * Background color of the ShowCase.
     *  - #3F51B5 color will be set if this param is not defined
     */
    fun backgroundColorResourceId(colorResId: Int): ShowCaseBuilder {
        mBackgroundColor = ContextCompat.getColor(mActivity!!.get(), colorResId)
        return this
    }

    /**
     * Text color of the ShowCase.
     *  - White color will be set if this param is not defined
     */
    fun textColor(color: Int): ShowCaseBuilder {
        mTextColor = color
        return this
    }

    /**
     * Text color of the ShowCase.
     *  - White color will be set if this param is not defined
     */
    fun textColorResourceId(colorResId: Int): ShowCaseBuilder {
        mTextColor = ContextCompat.getColor(mActivity!!.get(), colorResId)
        return this
    }

    /**
     * Title text size in SP.
     * - Default value -> 16 sp
     */
    fun titleTextSize(textSize: Int): ShowCaseBuilder {
        mTitleTextSize = textSize
        return this
    }

    /**
     * Description text size in SP.
     * - Default value -> 14 sp
     */
    fun descriptionTextSize(textSize: Int): ShowCaseBuilder {
        mSubtitleTextSize = textSize
        return this
    }

    /**
     * If an unique id is passed in this function, this ShowCase will only be showed once
     * - ID to identify the ShowCase
     */
    fun showOnce(id: String): ShowCaseBuilder {
        mShowOnce = id
        return this
    }

    /**
     * Target view to be highlighted. Set a TargetView is essential to figure out ShowCase position
     * - If a target view is not defined, the ShowCase final position will be the center of the screen
     */
    fun targetView(targetView: View): ShowCaseBuilder {
        mTargetView = WeakReference(targetView)
        return this
    }

    /**
     * If this variable is true, when user clicks on the target, the showcase will not be dismissed
     *  Default value -> false
     */
    fun disableTargetClick(isDisabled: Boolean): ShowCaseBuilder {
        mDisableTargetClick = isDisabled
        return this
    }

    /**
     * If this variable is true, close action button will be gone
     *  Default value -> false
     */
    fun disableCloseAction(isDisabled: Boolean): ShowCaseBuilder {
        mDisableCloseAction = isDisabled
        return this
    }

    /**
     * Insert an arrowPosition to force the position of the ShowCase.
     * - ArrowPosition enum values: LEFT, RIGHT, TOP and DOWN
     * - If an arrow position is not defined, the ShowCase will be set in a default position depending on the targetView
     */
    fun arrowPosition(arrowPosition: ShowCase.ArrowPosition): ShowCaseBuilder {
        mArrowPositionList.clear()
        mArrowPositionList.add(arrowPosition)
        return this
    }

    /**
     * Insert a set of arrowPosition to force the position of the ShowCase.
     * - ArrowPosition enum values: LEFT, RIGHT, TOP and DOWN
     * - If the number of arrow positions is 0 or more than 1, ShowCase will be set on the center of the screen
     */
    fun arrowPosition(arrowPosition: List<ShowCase.ArrowPosition>): ShowCaseBuilder {
        mArrowPositionList.clear()
        mArrowPositionList.addAll(arrowPosition)
        return this
    }

    /**
     * Highlight mode. It represents the way that the target view will be highlighted
     * - VIEW_LAYOUT: Default value. All the view box is highlighted (the rectangle where the view is contained). Example: For a TextView, all the element is highlighted (characters and background)
     * - VIEW_SURFACE: Only the view surface is highlighted, but not the background. Example: For a TextView, only the characters will be highlighted
     */
    fun highlightMode(highlightMode: ShowCase.HighlightMode): ShowCaseBuilder {
        mHighlightMode = highlightMode
        return this
    }

    /**
     * Add a ShowCaseListener in order to listen the user actions:
     * - onTargetClick -> It is triggered when the user clicks on the target view
     * - onCloseClick -> It is triggered when the user clicks on the close icon
     */
    fun listener(ShowCaseListener: ShowCaseListener): ShowCaseBuilder {
        mShowCaseListener = ShowCaseListener
        return this
    }

    /**
     * Add a sequence listener in order to know when a ShowCase has been dismissed to show another one
     */
    internal fun sequenceListener(showCaseSequenceListener: ShowCaseSequenceListener): ShowCaseBuilder {
        mShowCaseSequenceListener = showCaseSequenceListener
        return this
    }

    internal fun isFirstOfSequence(isFirst: Boolean): ShowCaseBuilder {
        mIsFirstOfSequence = isFirst
        return this
    }

    internal fun isLastOfSequence(isLast: Boolean): ShowCaseBuilder {
        mIsLastOfSequence = isLast
        return this
    }

    /**
     * Build the ShowCase object from the builder one
     */
    private fun build(): ShowCase {
        if (mIsFirstOfSequence == null)
            mIsFirstOfSequence = true
        if (mIsLastOfSequence == null)
            mIsLastOfSequence = true

        return ShowCase(this)
    }

    /**
     * Show the ShowCase using the params added previously
     */
    fun show(): ShowCase {
        val showCase = build()
        if (mTargetView != null) {
            val targetView = mTargetView!!.get()
            if (targetView!!.height == 0 || targetView.width == 0) {
                //If the view is not already painted, we wait for it waiting for view changes using OnGlobalLayoutListener
                onGlobalLayoutListenerTargetView = ViewTreeObserver.OnGlobalLayoutListener {
                    showCase.show()
                    targetView.viewTreeObserver.removeOnGlobalLayoutListener(
                        onGlobalLayoutListenerTargetView
                    )
                }
                targetView.viewTreeObserver.addOnGlobalLayoutListener(
                    onGlobalLayoutListenerTargetView
                )
            } else {
                showCase.show()
            }
        } else {
            showCase.show()
        }
        return showCase
    }
}