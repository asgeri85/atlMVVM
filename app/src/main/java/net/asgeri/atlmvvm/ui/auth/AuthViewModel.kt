package net.asgeri.atlmvvm.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> get() = _isLogin

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun login(email: String, password: String) {
        _loading.value = true
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            Log.e("gelenData",it.isSuccessful.toString())
            _isLogin.value = it.isSuccessful
            _loading.value = false
        }
    }

    fun register(email: String, password: String) {
        _loading.value = true

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _isLogin.value = true
            } else {
                _error.value = it.exception?.localizedMessage
            }
            _loading.value = false
        }
    }

}