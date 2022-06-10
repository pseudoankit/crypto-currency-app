package lazycoder21.droid.crypto.utils

import androidx.fragment.app.Fragment

object Utils {
    val Fragment.expTag get() = javaClass.canonicalName
}