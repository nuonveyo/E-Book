package com.veyo.ebook.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.veyo.ebook.di.utilities.ViewModelFactory
import com.veyo.ebook.di.utilities.ViewModelKey
import com.veyo.ebook.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Veyo on 06-May-2019.
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindLoginViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookListViewModel::class)
    abstract fun bindBookListViewModel(viewModel: BookListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopBookListViewModel::class)
    abstract fun bindTopBookListViewModel(viewModel: TopBookListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookDetailViewModel::class)
    abstract fun bindBookDetailViewModel(viewModel: BookDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopBookDetailViewModel::class)
    abstract fun bindTopBookDetailViewModel(viewModel: TopBookDetailViewModel): ViewModel
}
