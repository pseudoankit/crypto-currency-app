package lazycoder21.droid.crypto.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.utils.SortOptions
import lazycoder21.droid.crypto.utils.Utils.mColor
import lazycoder21.droid.crypto.utils.Utils.mDimension
import lostankit7.droid.customview.FontAwesomeIcon
import lostankit7.droid.customview.FontAwesomeIconType
import lostankit7.droid.utils.updateTypeface

class FilterView @JvmOverloads constructor(
    context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    @SortOptions
    var sortOption: Int = SortOptions.ASCENDING
        private set
    var iconCTA: ((Int) -> Unit)? = null

    private lateinit var text: String
    private lateinit var ascIcon: String
    private lateinit var descIcon: String
    private var icon: FontAwesomeIcon? = null

    init {
        manageAttributes()
        initView()

    }

    private fun initView() {
        val textView = TextView(context).apply {
            text = this@FilterView.text
            setTextColor(context.mColor(R.color.white))
        }
        icon = FontAwesomeIcon(context).apply {
            text = ascIcon
            updateTypeface(FontAwesomeIconType.SOLID)
            setPadding(
                context.mDimension(R.dimen.dp_5).toInt(),
                paddingTop, paddingRight, paddingBottom
            )
            setTextColor(context.mColor(R.color.white))
        }

        orientation = LinearLayout.HORIZONTAL
        addView(textView)
        addView(icon)

        setOnClickListener { filterCTA() }
    }

    private fun filterCTA() {
        invertSortOptionAndUpdateIcon()
        iconCTA?.invoke(sortOption)
    }

    private fun invertSortOptionAndUpdateIcon() {
        sortOption = if (sortOption == SortOptions.ASCENDING) {
            icon?.text = descIcon
            SortOptions.DESCENDING
        } else {
            icon?.text = ascIcon
            SortOptions.ASCENDING
        }
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
        const val DEF_ASC_ICON = "\uf062"
        const val DEF_DESC_ICON = "\uf063"
    }
}