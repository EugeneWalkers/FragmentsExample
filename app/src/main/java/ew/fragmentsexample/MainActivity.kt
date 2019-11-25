package ew.fragmentsexample

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments_lifecycle_info.movementMethod = ScrollingMovementMethod()

        val factory = supportFragmentManager.fragmentFactory

        supportFragmentManager
            .beginTransaction()
            .applySelectedAction(
                factory.instantiate(
                    Fragment::class.java.classLoader,
                    FirstFragment::class.java.name
                ),
                FirstFragment::class.java.name
            )
            .commit()

        start_command.setOnClickListener {
            val shouldBeAddedToBackStack = shouldBeAddedToBackStack()

            val selectedFragment = getFragmentName()

            val fragmentInstance = factory.instantiate(
                Fragment::class.java.classLoader,
                selectedFragment
            )
            val tag = selectedFragment

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(shouldBeAddedToBackStack, selectedFragment)
                .applySelectedAction(fragmentInstance, tag)
                .commit()
        }

        back_stack_command.setOnClickListener {
            supportFragmentManager.popBackStack()
        }

        clear_command.setOnClickListener {
            i = 1
            fragments_lifecycle_info.text = null
        }
    }

    private fun shouldBeAddedToBackStack() = back_stack_box.isChecked

    private fun getFragmentName(): String {
        return when (fragments_group.checkedRadioButtonId) {
            R.id.fragment_1 -> FirstFragment::class.java.name
            R.id.fragment_2 -> SecondFragment::class.java.name
            R.id.fragment_3 -> ThirdFragment::class.java.name
            R.id.fragment_4 -> FourthFragment::class.java.name
            else -> throw IllegalStateException("Wrong fragment name")
        }
    }

    private fun FragmentTransaction.addToBackStack(addToBackStack: Boolean, fragmentName: String) =
        if (addToBackStack) {
            addToBackStack(fragmentName)
        } else {
            this
        }

    private fun FragmentTransaction.applySelectedAction(fragment: Fragment, fragmentTag: String) =
        when (actions_group.checkedRadioButtonId) {
            R.id.action_add ->
                add(
                    R.id.fragment_wrapper,
                    fragment,
                    fragmentTag
                )
            R.id.action_replace ->
                replace(
                    R.id.fragment_wrapper,
                    fragment,
                    fragmentTag
                )
            R.id.action_show ->
                supportFragmentManager.findFragmentByTag(fragmentTag)?.let {
                    show(it)
                } ?: this
            R.id.action_hide ->
                supportFragmentManager.findFragmentByTag(fragmentTag)?.let {
                    hide(it)
                } ?: this
            R.id.action_remove ->
                supportFragmentManager.findFragmentByTag(fragmentTag)?.let {
                    remove(it)
                } ?: this
            else -> throw IllegalStateException("Wrong action type")
        }

    fun registerCallback(fragment: Fragment, message: String) {
        fragments_lifecycle_info.append((i++).toString() + ": " + fragment::class.java.simpleName + ": " + message + System.lineSeparator())

        fragments_lifecycle_info.post {
            val scrollAmount =
                fragments_lifecycle_info.layout.getLineTop(fragments_lifecycle_info.lineCount) - fragments_lifecycle_info.height

            if (scrollAmount > 0) {
                fragments_lifecycle_info.scrollTo(0, scrollAmount)
            } else {
                fragments_lifecycle_info.scrollTo(0, 0)
            }
        }
    }
}
