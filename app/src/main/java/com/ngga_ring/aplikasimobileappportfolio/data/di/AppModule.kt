package com.ngga_ring.aplikasimobileappportfolio.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            AppDatabase::class.java,
//            "my-database"
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserDao(database: AppDatabase): UserDao {
//        return database.userDao()
//    }

}
