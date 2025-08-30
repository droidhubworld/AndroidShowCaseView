# AndroidShowCaseView
[![](https://jitpack.io/v/droidhubworld/AndroidShowCaseView.svg)](https://jitpack.io/#droidhubworld/AndroidShowCaseView)

ShowCase is an elegant and simple framework developed in Kotlin (usable also in Java) that let you to use informative ShowCase to help your users pointing out different features of your application or in your App onboarding. The basic use of the framework consists on a target element passed as input which will be highlighted over a translucent background and pointed out by a customizable ShowCase.


## Getting started

Add the library into your proyect is really easy, you just need to add this line in dependencies block in your app Gradle:
```groovy
implementation 'com.github.droidhubworld:AndroidShowCaseView:Tag'
```
**NOTE:** You can check the LATEST_VERSION in the version badge at the top of this file.

## Usage
### Basic sample

Create a new ShowCase is pretty straightforward. It is only needed an instance of the current activity and the target view to carry it out.
```kotlin
ShowCaseBuilder(this) //Activity instance
                .title("foo") //Any title for the ShowCase view
                .targetView(view) //View to point out
                .show() //Display the ShowCase
```
**NOTE:** If the target is not passed as input, the ShowCase will be located by default in the middle of the screen without arrows.

### Custom ShowCase

Below it is showed an example of a ShowCase will all possible input params which can be custom.

```kotlin
ShowCaseBuilder(this) //Activity instance
                .title("foo") //Any title for the ShowCase view
                .description("bar") //More detailed description
                .arrowPosition(ShowCase.ArrowPosition.RIGHT) //You can force the position of the arrow to change the location of the ShowCase.
                .backgroundColor(Color.GREEN) //ShowCase background color
                .textColor(Color.BLACK) //ShowCase Text color
                .titleTextSize(17) //Title text size in SP (default value 16sp)
                .descriptionTextSize(15) //Subtitle text size in SP (default value 14sp)
                .image(imageDrawable) //ShowCase main image
                .closeActionImage(CloseImageDrawable) //Custom close action image
                .showOnce("SHOW_CASE_ID") //Id to show only once the ShowCase
                .listener(listener(object : ShowCaseListener{ //Listener for user actions
                    override fun onTargetClick(showCase: ShowCase) {
                        //Called when the user clicks the target
                    }
                    override fun onCloseActionImageClick(showCase: ShowCase) {
                        //Called when the user clicks the close button
                    }
                    override fun onShowCaseClick(showCase: ShowCase) {
                        //Called when the user clicks on the ShowCase
                    }

                    override fun onBackgroundDimClick(showCase: ShowCase) {
                        //Called when the user clicks on the background dim
                    }
                })
                .targetView(view) //View to point out
                .show() //Display the ShowCase
```

**NOTE:** Set more than one arrow position is allowed. For that case, the arrows will be painted and the ShowCase will be located in the middle of the screen. It could be useful to indicate a swipe or a scroll movement.

### ShowCaseSequence sample

It has been also implemented a ShowCaseSequence object in order to chain more than one ShowCase. It could be useful when it is desired to point out several things at the same moment.
```kotlin
ShowCaseSequence()
                .addShowCase(firstShowCaseBuilder) //First ShowCase to show
                .addShowCase(secondShowCaseBuilder) // This one will be showed when firstShowCase is dismissed
                .addShowCase(thirdShowCaseBuilder) // This one will be showed when secondShowCase is dismissed
                .show() //Display the ShowCaseSequence
```

For more information and examples, please check our [sample app](/app).
If you have any issues or feedback, please visit [issue section](https://github.com/droidhubworld/AndroidShowCaseView/issues).
Please feel free to collaborate with us making this framework as best as possible.

### Handel Back Press

Below it is showed an example for handel back press.

## Add Back Pressed Dispatcher linke
```kotlin

private var allowBackPress = false
fun setAllowBackPress(allow: Boolean) {
   allowBackPress = allow
}
```
## onCreate addCallback
```
onBackPressedDispatcher.addCallback(this) {
  if (allowBackPress) {
      // Block back press
  } else {
      isEnabled = false
      onBackPressedDispatcher.onBackPressed()
  }
}
```
## And add ShowCase listener and override
```
override fun onShowCaseShow(showCase: ShowCase) {
    setAllowBackPress(true)
}

override fun onShowCaseTargetClick(showCase: ShowCase) {

}

override fun onShowCaseCloseActionClick(showCase: ShowCase,skip: Boolean) {
    setAllowBackPress(false)
}

override fun onShowCaseBackgroundDimClick(showCase: ShowCase) {
    setAllowBackPress(false)
}

override fun onShowCaseClick(showCase: ShowCase) {
}
```
## License

`ShowCase-Android` is available under the MIT license. See the [LICENSE](/LICENSE) file for more info.
