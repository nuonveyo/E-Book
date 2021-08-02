package com.veyo.ebook.view.acitivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.veyo.ebook.R
import com.veyo.ebook.databinding.ActivityMainBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Veyo Nuon on 7/30/2021.
 */
class MainActivity : BindingActivity<ActivityMainBinding>(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AnimationActivity)
        super.onCreate(savedInstanceState)
    }
}