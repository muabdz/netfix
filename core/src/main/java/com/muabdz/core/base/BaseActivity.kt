package com.muabdz.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.muabdz.core.exception.ApiErrorException
import com.muabdz.core.exception.NoInternetConnectionException
import com.muabdz.core.utils.getErrorMessage
import com.muabdz.netfix.R
import java.lang.Exception

abstract class BaseActivity<B : ViewBinding, VM : ViewModel>(
    val bindingFactory : (LayoutInflater) -> B
) : AppCompatActivity() {

    protected lateinit var binding: B
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initView()
        observeData()
    }

    abstract fun initView()

    open fun observeData() {

    }

    open fun showError(isErrorEnabled: Boolean, exception: Exception) {
        if (isErrorEnabled) {
            Toast.makeText(this, getErrorMessage(exception), Toast.LENGTH_SHORT).show()
        }
    }

    fun enableHomeAsBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

}