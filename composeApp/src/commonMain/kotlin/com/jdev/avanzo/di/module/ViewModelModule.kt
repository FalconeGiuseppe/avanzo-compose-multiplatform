package com.jdev.avanzo.di.module

import com.jdev.avanzo.presentation.FoodViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    singleOf(::FoodViewModel)
}