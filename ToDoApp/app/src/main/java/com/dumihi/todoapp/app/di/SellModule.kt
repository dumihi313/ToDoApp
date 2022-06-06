package com.dumihi.todoapp.app.di

import androidx.lifecycle.ViewModel
import com.dumihi.todoapp.app.presentation.buy.BuyFragment
import com.dumihi.todoapp.app.presentation.buy.BuyViewModel
import com.dumihi.todoapp.app.presentation.sell.SellFragment
import com.dumihi.todoapp.app.presentation.sell.SellViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class SellModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSellFragment(): SellFragment

    @Binds
    @IntoMap
    @ViewModelKey(SellViewModel::class)
    internal abstract fun bindCallSellViewModel(viewModel: SellViewModel): ViewModel
}