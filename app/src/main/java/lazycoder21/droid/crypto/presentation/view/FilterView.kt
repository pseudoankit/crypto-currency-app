package lazycoder21.droid.crypto.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.utils.Utils.fastLazy
import lostankit7.droid.customview.FontAwesomeIcon

class FilterView @JvmOverloads constructor(
    context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var text: String
    private lateinit var ascIcon: String
    private lateinit var descIcon: String
    private val textView by fastLazy { createTextView() }
    private val icon by fastLazy { createSortIcon() }

    init {
        manageAttributes()


    }

    private fun createTextView() = TextView(context).apply {
        text = this@FilterView.text
        setOnClickListener { filterCTA() }
    }

    private fun createSortIcon() = FontAwesomeIcon(context).apply {
        text = ascIcon
        setOnClickListener { filterCTA() }
    }

    private fun filterCTA() {

    }

    private fun manageAttributes() {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.FilterView)
        try {
            text = arr.getString(R.styleable.FilterView_filter_name) ?: ""
            ascIcon = arr.getString(R.styleable.FilterView_asc_icon) ?: DEF_ASC_ICON
            descIcon = arr.getString(R.styleable.FilterView_desc_icon) ?: DEF_DESC_ICON
        } catch (e: Exception) {
        }
        arr.recycle()
    }

    companion object {
        const val DEF_ASC_ICON = "\uf176"
        const val DEF_DESC_ICON = "\uf175"
    }
}