package com.barkatme.demo

import android.app.Application
import com.barkatme.data.dataModule
import com.barkatme.demo.channel.ChannelsViewModel
import com.barkatme.demo.domain.domainModule
import com.barkatme.demo.flow.FlowViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val presentationModule = module {
    viewModel { FlowViewModel(get()) }
    viewModel { ChannelsViewModel() }
}

@ExperimentalCoroutinesApi
@FlowPreview
val startPresentationKoin = { application: Application ->
    startKoin {
        androidContext(application)
        modules(dataModule, domainModule, presentationModule)
        logger(AndroidLogger())
    }
}