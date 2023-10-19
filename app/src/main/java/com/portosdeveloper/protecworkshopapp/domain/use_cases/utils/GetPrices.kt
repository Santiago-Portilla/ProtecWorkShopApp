package com.portosdeveloper.protecworkshopapp.domain.use_cases.utils

import com.portosdeveloper.protecworkshopapp.domain.repository.UtilsRepository
import javax.inject.Inject

class GetPrices @Inject constructor(private val repository: UtilsRepository) {

    operator fun invoke() = repository.getPrices()

}