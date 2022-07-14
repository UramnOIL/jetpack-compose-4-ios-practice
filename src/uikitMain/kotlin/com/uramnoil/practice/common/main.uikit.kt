/*
 * Copyright 2020-2021 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

// package文を書くとエラー

// Use `xcodegen` first, then `open ./ComposeFallingBalls.xcodeproj` and then Run button in XCode.
import androidx.compose.ui.window.Application
import com.uramnoil.practice.common.App
import kotlinx.cinterop.*
import platform.UIKit.*
import platform.Foundation.*

fun main() {
    val args = emptyArray<String>()
    // argv.sizeだとエラー
    val argc = args.size + 1
    // スコープ終了時にスコープ内で割り当てられたメモリを自動的に廃棄するブロック
    memScoped {
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        // https://developer.apple.com/documentation/foundation/nsautoreleasepool
        autoreleasepool {
            // https://developer.apple.com/documentation/uikit/1622933-uiapplicationmain
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    @OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: Map<Any?, *>?): Boolean {
        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window!!.rootViewController = Application("Jetpack Compose for iOS") {
            App()
        }
        window!!.makeKeyAndVisible()
        return true
    }
}