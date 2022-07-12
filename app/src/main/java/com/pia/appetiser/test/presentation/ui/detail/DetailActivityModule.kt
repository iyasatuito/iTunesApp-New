package com.pia.appetiser.test.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import com.pia.appetiser.test.presentation.di.PerActivity
import com.pia.appetiser.test.presentation.di.modules.BaseActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class DetailActivityModule {

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: DetailActivity): AppCompatActivity = activity
}