package com.example.lymidateproject.fragment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lymidateproject.R
import kotlinx.android.synthetic.main.fragment_login_phone.*


class LoginPhone : Fragment(R.layout.fragment_login_phone), AdapterView.OnItemSelectedListener,
    View.OnClickListener {

    var navController: NavController? = null
    var list_of_number = arrayOf("+90", "+47", "+48", "+90", "+90", "+90", "+90", "+90", "+90")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_resume).setOnClickListener(this)

        spn_country_code.onItemSelectedListener = this
        val code = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, list_of_number)
        code.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spn_country_code!!.adapter = code
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, positon: Int, id: Long) {
    }

    override fun onClick(v: View?) {
        navController?.navigate(R.id.action_loginPhone_to_credentialsFragment)
    }
}
