package com.example.university.di

import android.content.Context
import android.content.ContextWrapper
import androidx.annotation.StringRes
import androidx.room.Room
import com.example.university.data.login.UserDAO
import com.example.university.data.login.UserDatabase
import com.example.university.ui.log_in.repository.UserDaoRepository
import com.example.university.ui.log_in.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideUserDB(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(appContext, UserDatabase::class.java, "user-database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDAO {
        return userDatabase.userDAO()
    }

    @Provides
    @Singleton
    fun provideUserDaoImpl(userDAO: UserDAO): UserDaoRepository {
        return UserRepositoryImpl(userDAO)
    }

    @Provides
    @Singleton
    fun provideStringResourceProvider(@ApplicationContext context: Context): StringResourceProvider {
        return object : StringResourceProvider {
            override fun getString(@StringRes resId: Int): String {
                return context.getString(resId)
            }
        }
    }


}