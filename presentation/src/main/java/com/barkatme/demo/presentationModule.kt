package com.barkatme.demo

import android.app.Application
import com.barkatme.data.dataModule
import com.barkatme.demo.domain.domainModule
import com.barkatme.demo.ui.main.coroutines.channel.CoroutinesChannelViewModel
import com.barkatme.demo.ui.main.coroutines.flow.CoroutinesFlowViewModel
import com.barkatme.demo.ui.main.coroutines.menu.CoroutinesMenuViewModel
import com.barkatme.demo.ui.main.jetpack.menu.JetpackMenuViewModel
import com.barkatme.demo.ui.main.room.giphy.RoomGiphyViewModel
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

    //coroutines
    viewModel { CoroutinesMenuViewModel() }
    viewModel { CoroutinesFlowViewModel(get()) }
    viewModel { CoroutinesChannelViewModel() }

    //jetpack
    viewModel { JetpackMenuViewModel() }

    //room
    viewModel { RoomGiphyViewModel() }
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