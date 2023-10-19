package com.portosdeveloper.protecworkshopapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.portosdeveloper.protecworkshopapp.core.Constants.UTILS
import com.portosdeveloper.protecworkshopapp.domain.module.Prices
import com.portosdeveloper.protecworkshopapp.domain.module.User
import com.portosdeveloper.protecworkshopapp.domain.repository.UtilsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class UtilsRepositoryImpl @Inject constructor(
    @Named(UTILS) private val utilsRef: CollectionReference
) : UtilsRepository {

    override fun getPrices(): Flow<Prices> = callbackFlow{
        val snapshotListener = utilsRef.document("Prices").addSnapshotListener{
                snapshot,e ->
            val prices = snapshot?.toObject(Prices :: class.java) ?: Prices()
            trySend(prices)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

}