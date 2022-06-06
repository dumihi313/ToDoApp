package com.dumihi.todoapp.app.di

import androidx.lifecycle.ViewModel
import com.dumihi.todoapp.app.presentation.buy.BuyFragment
import com.dumihi.todoapp.app.presentation.buy.BuyViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class BuyModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeBuyFragment(): BuyFragment

    @Binds
    @IntoMap
    @ViewModelKey(BuyViewModel::class)
    internal abstract fun bindCallBuyViewModel(viewModel: BuyViewModel): ViewModel
}