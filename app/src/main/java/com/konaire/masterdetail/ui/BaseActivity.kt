package com.konaire.masterdetail.ui

import android.view.MenuItem

import com.konaire.masterdetail.R
import com.konaire.masterdetail.util.Navigation

import dagger.android.support.DaggerAppCompatActivity

import javax.inject.Inject

/**
 * Created by Evgeny Eliseyev on 14/07/2018.
 */
abstract class BaseActivity: DaggerAppCompatActivity() {
    @Inject lateinit var navigation: Navigation

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navigation.closeFragment(this)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as BaseFragment
        if (fragment.defaultBackButtonBehaviour()) {
            super.onBackPressed()
        }
    }

    fun showFragment(fragment: BaseFragment) = navigation.showFragment(this, fragment)
}