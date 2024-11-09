package com.bruno13palhano.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Dispatcher(val dispatcher: LociDispatchers)

internal enum class LociDispatchers {
    IO,
    DEFAULT
}