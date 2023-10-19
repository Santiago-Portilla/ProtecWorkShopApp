package com.portosdeveloper.protecworkshopapp.domain.use_cases.packageShirt

import com.portosdeveloper.protecworkshopapp.domain.repository.PackageRepository
import javax.inject.Inject

class GetPackageList @Inject constructor(private val repository: PackageRepository) {
    operator fun invoke() = repository.getPackageList()
}