package ew.fragmentsexample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    @LayoutRes
    abstract fun getLayout(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        log("onCreateView")
        (activity as? MainActivity)?.registerCallback(this, "onCreateView")

        // Inflate the layout for this fragment
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        log("onAttach")
        (activity as? MainActivity)?.registerCallback(this, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        log("onCreate")
        (activity as? MainActivity)?.registerCallback(this, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        log("onViewCreated")
        (activity as? MainActivity)?.registerCallback(this, "onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        log("onActivityCreated")
        (activity as? MainActivity)?.registerCallback(this, "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()

        log("onStart")
        (activity as? MainActivity)?.registerCallback(this, "onStart")
    }

    override fun onResume() {
        super.onResume()

        log("onResume")
        (activity as? MainActivity)?.registerCallback(this, "onResume")
    }

    override fun onPause() {
        super.onPause()

        log("onPause")
        (activity as? MainActivity)?.registerCallback(this, "onPause")
    }

    override fun onStop() {
        super.onStop()

        log("onStop")
        (activity as? MainActivity)?.registerCallback(this, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        log("onDestroyView")
        (activity as? MainActivity)?.registerCallback(this, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()

        log("onDestroy")
        (activity as? MainActivity)?.registerCallback(this, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()

        log("onDetach")
        (activity as? MainActivity)?.registerCallback(this, "onDetach")
    }

}