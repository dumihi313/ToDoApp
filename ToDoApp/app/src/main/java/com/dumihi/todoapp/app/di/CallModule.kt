package com.dumihi.todoapp.app.di

import androidx.lifecycle.ViewModel
import com.dumihi.todoapp.app.presentation.call.CallListFragment
import com.dumihi.todoapp.app.presentation.call.CallListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class CallModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeCallListFragment(): CallListFragment

    @Binds
    @IntoMap
    @ViewModelKey(CallListViewModel::class)
    internal abstract fun bindCallListViewModel(viewModel: CallListViewModel): ViewModel
}