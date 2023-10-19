package com.portosdeveloper.protecworkshopapp.domain.use_cases.packageShirt

import com.portosdeveloper.protecworkshopapp.domain.repository.PackageRepository
import javax.inject.Inject

class GetPackageById @Inject constructor(private val repository: PackageRepository) {
    operator fun invoke(id: String) = repository.getPackageById(id)
}