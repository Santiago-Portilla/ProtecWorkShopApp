package com.portosdeveloper.protecworkshopapp.domain.repository

import com.portosdeveloper.protecworkshopapp.domain.module.Prices
import kotlinx.coroutines.flow.Flow

interface UtilsRepository {
    fun getPrices(): Flow<Prices>
}