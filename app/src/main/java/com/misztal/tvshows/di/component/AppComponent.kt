package com.misztal.tvshows.di.component

import com.misztal.tvshows.App
import com.misztal.tvshows.di.module.AppModule
import com.misztal.tvshows.di.module.BuildersModule
import com.misztal.tvshows.di.module.NetworkModule
import com.misztal.tvshows.di.module.SchedulerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by kmisztal on 02.06.2017.
 *
 * @author Krzysztof Misztal
 */
@Singleton
@Component(modules = [BuildersModule::class, AndroidSupportInjectionModule::class, AppModule::class,
    NetworkModule::class, SchedulerModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}