package com.example.university.di

import androidx.annotation.StringRes
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StringResource

interface StringResourceProvider {
    @StringResource
    fun getString(@StringRes resId: Int): String
}