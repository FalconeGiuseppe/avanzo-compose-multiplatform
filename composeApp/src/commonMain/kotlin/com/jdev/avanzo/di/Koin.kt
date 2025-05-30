package com.jdev.avanzo.di

import com.jdev.avanzo.di.module.networkModule
import com.jdev.avanzo.di.module.repositoryModule
import com.jdev.avanzo.di.module.serviceModule
import com.jdev.avanzo.di.module.useCaseModule
import com.jdev.avanzo.di.module.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(networkModule, repositoryModule, viewModelModule, useCaseModule, serviceModule)
    }
}