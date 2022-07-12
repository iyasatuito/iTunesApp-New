package com.pia.appetiser.test.presentation.ui.search

import androidx.appcompat.app.AppCompatActivity
import com.pia.appetiser.test.presentation.di.PerActivity
import com.pia.appetiser.test.presentation.di.modules.BaseActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class SearchItunesActivityModule {


//    @Provides
//    fun provideSearchItemMovieViewHolderFactory(activity: SearchItunesActivity): ViewHolderFactory<BindingViewHolder<DisplayableItunesDetails>> =
//        SearchItunesMediaItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: SearchItunesActivity): AppCompatActivity = activity
}