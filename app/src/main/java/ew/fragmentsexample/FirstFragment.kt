package ew.fragmentsexample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : BaseFragment() {

    override fun getLayout(): Int {
        return R.layout.fragment_first
    }

}
