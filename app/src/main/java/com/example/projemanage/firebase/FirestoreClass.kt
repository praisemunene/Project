package com.example.projemanage.firebase

import android.util.Log
import com.example.projemanage.activities.SignInActivity
import com.example.projemanage.activities.SignUpActivity
import com.example.projemanage.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.example.projemanage.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: com.example.projemanage.models.User){
        mFirestore.collection(Constants.USERS).document(getCurrentUserId()).set(userInfo,
            SetOptions.merge()).addOnSuccessListener {
                activity.userRegisteredSuccess()
        }.addOnFailureListener{
            e ->
            Log.e(activity.javaClass.simpleName,"Error Failed to get user info")
        }
    }

    fun signInUser(activity: SignInActivity){
        mFirestore.collection(Constants.USERS).document(getCurrentUserId()).get()
            .addOnSuccessListener {document ->
                val loggedInUser = document.toObject(User::class.java)
                if(loggedInUser != null)
                    activity.signInSucces(loggedInUser)
        }.addOnFailureListener{
                e ->
            Log.e("SignInUser","Error Failed to get user info",e)
        }
    }


    fun getCurrentUserId():String{

        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if(currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
}