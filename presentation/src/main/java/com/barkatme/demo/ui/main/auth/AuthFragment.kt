package com.barkatme.demo.ui.main.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment(private val layout: Int = R.layout.fragment_auth) : Fragment(layout) {

    private val viewModel: AuthViewModel by viewModel()

    private var _binding: FragmentAuthBinding? = null
    val binding: FragmentAuthBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentAuthBinding>(
            inflater,
            layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onAuthCompleted = { token ->
            Log.d(AuthViewModel.TAG, "auth token: $token")
            Toast.makeText(context, token.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}