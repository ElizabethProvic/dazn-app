package com.dazn.playerapp.di

import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.domain.GetDataUseCase
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: PlayerService)

    fun inject(useCase: GetDataUseCase)
}

