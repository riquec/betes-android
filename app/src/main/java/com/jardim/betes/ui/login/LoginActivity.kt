package com.jardim.betes.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jardim.betes.R
import com.jardim.betes.ui.login.create.CreateUserFragment
import com.jardim.betes.ui.login.login.LoginFragment
import com.jardim.betes.ui.login.validate.ValidateUserFragment
import com.jardim.betes.utils.constants.FragmentsKey.CREATE_USER_FRAGMENT_KEY
import com.jardim.betes.utils.constants.FragmentsKey.LOGIN_FRAGMENT_KEY
import com.jardim.betes.utils.constants.FragmentsKey.VALIDATE_USER_FRAGMENT_KEY
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(), LoginNavigate {
    private val viewModel : LoginViewModel by inject()

    override fun onResume() {
        super.onResume()

        replaceFragment(viewModel.currentFragment)
    }
    override fun goToCreateUser() {
        replaceFragment(CREATE_USER_FRAGMENT_KEY)
    }

    override fun goToLogin() {
        replaceFragment(LOGIN_FRAGMENT_KEY)
    }

    override fun goToValidatUser() {
        replaceFragment(VALIDATE_USER_FRAGMENT_KEY)
    }

    override fun goToMenu() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun replaceFragment(fragmentTag : String) {
        val fragment = when (fragmentTag) {
            LOGIN_FRAGMENT_KEY -> LoginFragment.newInstance(this)
            CREATE_USER_FRAGMENT_KEY -> CreateUserFragment.newInstance(this)
            VALIDATE_USER_FRAGMENT_KEY -> ValidateUserFragment.newInstance(this)
            else -> null
        }

        fragment?.let {
            if (it.isVisible.not()) {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.login_frame_content_fragment, fragment, fragmentTag)
                    addToBackStack(fragmentTag)
                    commit()
                }
            }

            viewModel.currentFragment = fragmentTag
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
