package com.muabdz.login.presentation.ui

import android.content.Intent
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.muabdz.core.base.BaseActivity
import com.muabdz.core.exception.FieldErrorException
import com.muabdz.login.constants.LoginFieldConstants
import com.muabdz.login.databinding.ActivityLoginBinding
import com.muabdz.shared.router.ActivityRouter
import com.muabdz.shared.utils.ext.subscribe
import com.muabdz.shared.utils.listen
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(ActivityLoginBinding::inflate) {
    override val viewModel: LoginViewModel by inject()

    private val router : ActivityRouter by inject()

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(
                binding.etEmail.text?.trim().toString(),
                binding.etPassword.text?.trim().toString()
            )
        }
        binding.btnAlreadyHaveAccount.setOnClickListener {
            startActivity(router.registerActivity(this))
        }
        binding.etPassword.listen(beforeTextChanged =  {
            binding.tilPassword.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        })
    }

    override fun observeData() {
        viewModel.loginResult.observe(this) { loginResult ->
            resetField()
            loginResult.subscribe(
                doOnSuccess =  {
                    showLoading(false)
                    navigateToHome()
                },
                doOnError = {
                    showLoading(false)
                    if (loginResult.exception is FieldErrorException) {
                        handleFieldError(loginResult.exception as FieldErrorException)
                    } else {
                        loginResult.exception?.let { e -> showError(true, e) }
                    }
                },
                doOnLoading = {
                    showLoading(true)
                }
            )

        }
    }

    private fun handleFieldError(exception: FieldErrorException) {
        exception.errorFields.forEach { errorField ->
            if (errorField.first == LoginFieldConstants.FIELD_EMAIL) {
                binding.etEmail.error = getString(errorField.second)
            }
            if (errorField.first == LoginFieldConstants.FIELD_PASSWORD) {
                binding.tilPassword.endIconMode = TextInputLayout.END_ICON_NONE
                binding.etPassword.error = getString(errorField.second)
            }
        }
    }

    private fun showLoading(isShowLoading : Boolean) {
        binding.pbLoading.isVisible = isShowLoading
    }

    private fun resetField() {
        binding.tilEmail.isErrorEnabled = false
        binding.tilPassword.isErrorEnabled = false
        binding.tilPassword.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
    }

    private fun navigateToHome() {
        startActivity(router.homeActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }
}