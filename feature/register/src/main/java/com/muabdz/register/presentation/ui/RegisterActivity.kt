package com.muabdz.register.presentation.ui

import com.muabdz.core.base.BaseActivity
import com.muabdz.register.databinding.ActivityRegisterBinding
import org.koin.android.ext.android.inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(ActivityRegisterBinding::inflate) {
    override val viewModel: RegisterViewModel by inject()

    override fun initView() {
    }

}