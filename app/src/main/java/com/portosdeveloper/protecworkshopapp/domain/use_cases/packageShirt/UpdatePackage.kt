package com.portosdeveloper.protecworkshopapp.domain.use_cases.packageShirt

import com.portosdeveloper.protecworkshopapp.domain.module.Package
import com.portosdeveloper.protecworkshopapp.domain.repository.PackageRepository
import javax.inject.Inject


class UpdatePackage @Inject constructor(private val repository: PackageRepository) {

    suspend operator fun invoke(packageShirt: Package, personal: String, job: String) = repository.update(packageShirt,personal,job)
}