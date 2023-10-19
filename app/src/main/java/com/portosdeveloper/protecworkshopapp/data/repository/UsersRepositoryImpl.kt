package com.portosdeveloper.protecworkshopapp.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import com.portosdeveloper.protecworkshopapp.core.Constants.USERSWORKSHOP
import com.portosdeveloper.protecworkshopapp.domain.module.Package
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.domain.module.User
import com.portosdeveloper.protecworkshopapp.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(USERSWORKSHOP) private val userWorkShopRef: CollectionReference
) : UsersRepository {



    override suspend fun update(user: User, newCamp: String): Response<Boolean> {
        return try{
            userWorkShopRef.document(user.id).
                update(FieldPath.of("workListInProgress"), FieldValue.arrayUnion(newCamp)).await()
            Response.Success(true)

        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun updateSalary(user: User, workDay: String, workMonth: String, workWeek: String, workLife: String): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            map["workDay"] = workDay
            map["workMonth"] = workMonth
            map["workWeek"] = workWeek
            map["workLife"] = workLife
            userWorkShopRef.document(user.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun getUser(id: String): User {
        val getUser = userWorkShopRef.document(id).get().await()
        return if(getUser.exists()) {
            getUser.toObject(User :: class.java)!!
        }else{
            User()
        }
    }
    override suspend fun updateFinish(user: User, newCamp: String): Response<Boolean> {
        return try{
            userWorkShopRef.document(user.id).
            update(FieldPath.of("workListFinish"), FieldValue.arrayUnion(newCamp)).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }
    override suspend fun updateProcess(user: User, newList: List<String>): Response<Boolean> {
        return try{
            val map : MutableMap<String, Any> = hashMapOf()
            map["workListInProgress"] = newList
            userWorkShopRef.document(user.id).update(map).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<Response<User>> = callbackFlow{
        val snapshotListener = userWorkShopRef.document(id).addSnapshotListener{
                snapshot,e ->

            val userResponse = if(snapshot != null){
                val user = snapshot.toObject(User:: class.java) ?: User()
                Response.Success(user)
            }else{
                Response.Failure(e!!)
            }
            trySend(userResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }
    override fun getLastUserById(id: String): Flow<Response<User>> = callbackFlow{
        val snapshotListener = userWorkShopRef.document(id).addSnapshotListener{
                snapshot,e ->

            val userResponse = if(snapshot != null){
                val user = snapshot.toObject(User :: class.java) ?: User()
                Response.Success(user)
            }else{
                Response.Failure(e!!)
            }
            trySend(userResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun getUserList(): Flow<List<String>> = callbackFlow {

        val snapshotListener = userWorkShopRef.addSnapshotListener{
                    snapshot,e ->
            val users = snapshot!!.toObjects(User :: class.java)
            val idUserList = ArrayList<String>()
            users.forEach {
                idUserList.add(it.id)
            }
            trySend(idUserList)
            }
        awaitClose{
            snapshotListener.remove()
        }

    }

    override fun getMergedUser(): Flow<List<String>> = callbackFlow{
        val snapshotListener = userWorkShopRef.whereEqualTo("job","Fusionado").addSnapshotListener{
                snapshot,e ->
            val usersMerged = snapshot!!.toObjects(User :: class.java)
            val idUserMergedList = ArrayList<String>()
            usersMerged.forEach {
                idUserMergedList.add(it.id)
            }
            trySend(idUserMergedList)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

}