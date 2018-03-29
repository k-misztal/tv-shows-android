package com.misztal.tvshows.di.module

import com.misztal.tvshows.di.qualifier.ComputationScheduler
import com.misztal.tvshows.di.qualifier.IOScheduler
import com.misztal.tvshows.di.qualifier.MainScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulerModule {

    @Provides
    @MainScheduler
    fun provideMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @IOScheduler
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @ComputationScheduler
    fun provideComputationScheduler(): Scheduler {
        return Schedulers.computation()
    }
}

