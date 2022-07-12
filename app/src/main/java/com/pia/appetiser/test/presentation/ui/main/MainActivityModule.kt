package com.pia.appetiser.test.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.pia.appetiser.test.presentation.di.PerActivity
import com.pia.appetiser.test.presentation.di.modules.BaseActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class MainActivityModule {

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: MainActivity): AppCompatActivity = activity
}