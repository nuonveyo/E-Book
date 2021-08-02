package com.veyo.ebook.di.module

import com.veyo.ebook.view.acitivity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Veyo Nuon on 9/20/2019.
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}