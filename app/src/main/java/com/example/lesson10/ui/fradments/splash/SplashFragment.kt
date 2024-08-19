package com.example.lesson10.ui.fradments.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.lesson10.R
import com.example.lesson10.ui.OnAuthLaunch
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton

class SplashFragment : Fragment() {
    private val animatorSet = AnimatorSet()
    private var signInButton: SignInButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_spalsh, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signInButton = view.findViewById(R.id.sign_in_button)
        val image: ImageView = view.findViewById(R.id.splash_icon)
        startAnimation(image)

        val context = requireContext()
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("257353822802-sit9c3q0vsht18t22tqp98js808q2vus.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        val account = GoogleSignIn.getLastSignedInAccount(context)
        val activity = requireActivity() as OnAuthLaunch
        if (account == null) showSignInButton()
        else activity.showListFragment()
        signInButton?.setOnClickListener { activity.launch(googleSignInClient.signInIntent) }
    }

    private fun showSignInButton() {
        signInButton?.visibility = View.VISIBLE
        animatorSet.cancel()
    }

    private fun startAnimation(image: ImageView) {
        val scaleXAnimation = ObjectAnimator.ofFloat(image, View.SCALE_X, 0.5f, 1f)
        scaleXAnimation.repeatMode = ObjectAnimator.REVERSE
        scaleXAnimation.repeatCount = ObjectAnimator.INFINITE

        val scaleYAnimation = ObjectAnimator.ofFloat(image, View.SCALE_Y, 0.5f, 1f)
        scaleYAnimation.repeatMode = ObjectAnimator.REVERSE
        scaleYAnimation.repeatCount = ObjectAnimator.INFINITE

        animatorSet.playTogether(scaleXAnimation, scaleYAnimation)
        animatorSet.duration = 1000
        animatorSet.start()
    }
}