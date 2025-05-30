package com.jdev.avanzo.di.module

import com.jdev.avanzo.data.repository.FoodRepositoryImpl
import com.jdev.avanzo.domain.FoodRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::FoodRepositoryImpl).bind<FoodRepository>()
}