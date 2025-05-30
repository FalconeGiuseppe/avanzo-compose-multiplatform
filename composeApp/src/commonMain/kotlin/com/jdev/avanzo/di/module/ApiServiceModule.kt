package com.jdev.avanzo.di.module

import com.jdev.avanzo.data.remote.FoodService
import com.jdev.avanzo.data.remote.FoodServiceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val serviceModule = module {
    singleOf(::FoodServiceImpl).bind<FoodService>()
}