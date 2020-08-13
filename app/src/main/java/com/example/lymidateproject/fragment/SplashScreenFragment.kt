package com.example.lymidateproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lymidateproject.R
import kotlinx.android.synthetic.main.fragment_splash_screen.*


class SplashScreenFragment : Fragment() {
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val animationbutton = AnimationUtils.loadAnimation(view.context, R.anim.frombottom)
        val imageanim = AnimationUtils.loadAnimation(view.context, R.anim.fromtop)
        val btnGetStarted = view.findViewById(R.id.btn_getstarted) as Button
        btn_getstarted.startAnimation(animationbutton)
        txtsplash.startAnimation(animationbutton)
        splashheart.startAnimation(imageanim)
        btnGetStarted.setOnClickListener {
            navController?.navigate(R.id.action_splashScreenFragment_to_loginPageFragment)
        }
    }
}