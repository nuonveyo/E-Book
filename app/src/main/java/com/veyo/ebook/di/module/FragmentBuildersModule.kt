package com.veyo.ebook.di.module

import com.veyo.ebook.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Veyo on 7/31/2021.
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeBookListFragment(): BookListFragment

    @ContributesAndroidInjector
    abstract fun contributeTopBookListFragment(): TopBookListFragment

    @ContributesAndroidInjector
    abstract fun contributeBookDetailFragment(): BookDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeTopBookDetailFragment(): TopBookDetailFragment
}