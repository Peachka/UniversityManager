package com.example.university.ui.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.login.User
import com.example.university.ui.log_in.repository.UserDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val userRepo: UserDaoRepository
): ViewModel() {

    fun addUser(user: User){
        viewModelScope.launch {
            userRepo.addUser(user)
        }
    }
}