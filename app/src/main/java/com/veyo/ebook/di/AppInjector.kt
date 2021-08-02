package com.veyo.ebook.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.veyo.ebook.App
import com.veyo.ebook.di.component.DaggerAppComponent
import com.veyo.ebook.di.utilities.Injectable
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber

/**
 * Created by Veyo Nuon on 9/20/2019.
 */
class AppInjector private constructor() {
    companion object {
        fun init(myApp: App) {

            DaggerAppComponent
                .builder()
                .application(myApp)
                .build()
                .inject(myApp)

            myApp.registerActivityLifecycleCallbacks(object :
                Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(p0: Activity) {
                    //Stub
                }

                override fun onActivityStarted(p0: Activity) {
                    //Stub
                }

                override fun onActivityDestroyed(p0: Activity) {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivityStopped(p0: Activity) {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                    handleActivity(p0)
                }

                override fun onActivityResumed(p0: Activity) {
                    //To change body of created functions use File | Settings | File Templates.
                }

            })
        }

        fun handleActivity(activity: Activity) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }

            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(object :
                        FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentPreAttached(
                            fm: FragmentManager,
                            f: Fragment,
                            context: Context,
                        ) {
                            super.onFragmentPreAttached(fm, f, context)
                            Timber.e("onFragmentPreAttached: %s", f)
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true)
            }
        }
    }
}