package com.portosdeveloper.protecworkshopapp.domain.repository

import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.domain.module.Package
import kotlinx.coroutines.flow.Flow

interface PackageRepository {
    suspend fun update(packageShirt: Package,personal: String, job: String): Response<Boolean>
    fun getPackageById(id: String): Flow<Response<Package>>
    fun getPackageList(): Flow<List<String>>
}