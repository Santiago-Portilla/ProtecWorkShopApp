package com.portosdeveloper.protecworkshopapp.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.domain.module.User

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password : String): Response<FirebaseUser>
}