package com.portosdeveloper.protecworkshopapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.portosdeveloper.protecworkshopapp.core.Constants.PACKAGE
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.domain.repository.PackageRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import com.portosdeveloper.protecworkshopapp.domain.module.Package
import com.portosdeveloper.protecworkshopapp.domain.module.User


class PackageRepositoryImpl @Inject constructor(
    @Named(PACKAGE) private val packageRef: CollectionReference
    ): PackageRepository {

    override suspend fun update(packageShirt: Package, personal: String, job : String): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            when(packageShirt.ubication){
                "Corte" ->{
                    map["mergedJob"] = personal
                    map["ubication"] = "Fusionado"
                }
                "Fusionado" ->{
                    when(job){
                        "CuellosPuños" ->{
                            if(packageShirt.cutNecksFistsJob != ""){
                                map["necksFistsJob"] = personal
                            }else{
                                map["cutNecksFistsJob"] = personal
                            }
                        }
                        "Fusionado" ->{
                            if(packageShirt.cutNecksFistsJob != "" && packageShirt.necksFistsJob != ""){
                                map["mergedFistsNecks"] = personal
                            }
                        }
                        else ->{
                            map["bodiesJob"] = personal
                            map["ubication"] = "Cuerpos"
                        }
                    }
                }
                "Cuerpos" ->{
                    when(job){
                        "CuellosPuños" ->{
                            if(packageShirt.cutNecksFistsJob != ""){
                                map["necksFistsJob"] = personal
                            }else{
                                map["cutNecksFistsJob"] = personal
                            }
                        }
                        "Fusionado" ->{
                            if(packageShirt.cutNecksFistsJob != "" && packageShirt.necksFistsJob != ""){
                                map["mergedFistsNecks"] = personal
                            }
                        }
                        else ->{
                            map["closedJob"] = personal
                            map["ubication"] = "Cerradora"
                        }
                    }
                }
                "Cerradora" ->{
                    when(job){
                        "CuellosPuños" ->{
                            if(packageShirt.cutNecksFistsJob != ""){
                                map["necksFistsJob"] = personal
                            }else{
                                map["cutNecksFistsJob"] = personal
                            }
                        }
                        "Fusionado" ->{
                            if(packageShirt.cutNecksFistsJob != "" && packageShirt.necksFistsJob != ""  && packageShirt.mergedFistsNecks == ""){
                                map["mergedFistsNecks"] = personal
                            }
                            else {
                                map["mergedClosed"] = personal
                                map["ubication"] = "CerradoraOk"
                            }
                        }
                    }
                }
                "CerradoraOk" ->{
                    map["terminationJob"] = personal
                    map["ubication"] = "Terminacion"
                }
                "Terminacion" ->{
                    map["buttonHoleButtonJob"] = personal
                    map["ubication"] = "Ojal y Boton"
                }
                "Ojal y Boton" ->{
                    map["polishJob"] = personal
                    map["ubication"] = "Remate"
                }
                "Remate" ->{
                    map["packingJob"] = personal
                    map["ubication"] = "Empaque"
                }
            }
            packageRef.document(packageShirt.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getPackageById(id: String): Flow<Response<Package>> = callbackFlow{
        val snapshotListener = packageRef.document(id).addSnapshotListener{
                snapshot,e ->

            val packageResponse = if(snapshot != null){
                val packageShirt = snapshot.toObject(Package:: class.java) ?: Package()
                Response.Success(packageShirt)
            }else{
                Response.Failure(e!!)
            }
            trySend(packageResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun getPackageList(): Flow<List<String>> = callbackFlow {

        val snapshotListener = packageRef.addSnapshotListener{
                snapshot,e ->
            val packageShirts = snapshot!!.toObjects(User :: class.java)
            val idPackagesList = ArrayList<String>()
            packageShirts.forEach {
                idPackagesList.add(it.id)
            }
            trySend(idPackagesList)
        }
        awaitClose{
            snapshotListener.remove()
        }

    }
}