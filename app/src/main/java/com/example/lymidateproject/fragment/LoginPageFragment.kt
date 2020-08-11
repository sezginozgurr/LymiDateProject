package com.example.lymidateproject.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lymidateproject.R


class LoginPageFragment : Fragment(R.layout.fragment_login_page), View.OnClickListener {

    var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.login_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.create_account).setOnClickListener(this)
        view.findViewById<Button>(R.id.login_phone).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_btn -> {
                navController?.navigate(R.id.action_loginPageFragment_to_homePageFragment)
            }
            R.id.create_account -> {
                navController?.navigate(R.id.action_loginPageFragment_to_registerFragment)
            }
            R.id.login_phone -> {
                navController?.navigate(R.id.action_loginPageFragment_to_loginPhone)
            }
        }
    }
}