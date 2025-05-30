package com.jdev.avanzo.di.module

import com.jdev.avanzo.domain.usecase.FoodUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::FoodUseCase)
}