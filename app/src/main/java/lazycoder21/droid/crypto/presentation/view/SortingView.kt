package lazycoder21.droid.crypto.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.utils.CryptoSortingUtils
import lazycoder21.droid.crypto.utils.Utils.mColor
import lazycoder21.droid.crypto.utils.Utils.mDimension
import lostankit7.droid.customview.FontAwesomeIcon
import lostankit7.droid.customview.FontAwesomeIconType
import lostankit7.droid.utils.updateTypeface

class SortingView @JvmOverloads constructor(
    context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    @CryptoSortingUtils.SortOrder
    var sortOrder: Int = CryptoSortingUtils.SortOrder.ASCENDING
        private set

    var iconCTA: ((@CryptoSortingUtils.SortOrder Int) -> Unit)? = null

    private var filterParams = FilterParams.default
    private var icon: FontAwesomeIcon? = null
    private var textView: TextView? = null

    init {
        manageAttributes()
        initView()

    }

    fun updateTextColor(isSelected: Boolean) {
        val color = context.mColor(if (isSelected) R.color.purple_200 else R.color.white)
        color.apply {
            textView?.setTextColor(this)
            icon?.setTextColor(this)
        }
    }

    fun initialize(
        filterName: String, ascIcon: String? = null, descIcon: String? = null,
    ) {
        filterParams.filterName = filterName
        if (ascIcon != null) {
            filterParams.ascIcon = ascIcon
        }
        if (descIcon != null) {
            filterParams.descIcon = descIcon
        }
        updateTextView()
        updateIcon()
    }

    private fun initView() {
        textView = TextView(context).apply {
            setTextColor(context.mColor(R.color.white))
        }
        icon = FontAwesomeIcon(context).apply {
            updateTypeface(FontAwesomeIconType.SOLID)
            setPadding(
                context.mDimension(R.dimen.dp_5).toInt(),
                paddingTop, paddingRight, paddingBottom
            )
            setTextColor(context.mColor(R.color.white))
        }
        updateTextView()
        updateIcon()

        orientation = LinearLayout.HORIZONTAL
        addView(textView)
        addView(icon)

        this.setOnClickListener { filterCTA() }
    }

    private fun filterCTA() {
        invertSortOptionAndUpdateIcon()
        iconCTA?.invoke(sortOrder)
    }

    private fun invertSortOptionAndUpdateIcon() {
        sortOrder = if (sortOrder == CryptoSortingUtils.SortOrder.ASCENDING) {
            CryptoSortingUtils.SortOrder.DESCENDING
        } else {
            CryptoSortingUtils.SortOrder.ASCENDING
        }
        updateIcon()
    }

    private fun updateTextView() {
        textView?.text = filterParams.filterName
    }

    private fun updateIcon() {
        icon?.text = if (sortOrder == CryptoSortingUtils.SortOrder.ASCENDING) {
            filterParams.descIcon
        } else {
            filterParams.ascIcon
        }
    }

    private fun manageAttributes() = with(filterParams) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.FilterView)
        try {
            filterName = arr.getString(R.styleable.FilterView_filter_name) ?: ""
            ascIcon = arr.getString(R.styleable.FilterView_asc_icon) ?: DEF_ASC_ICON
            descIcon = arr.getString(R.styleable.FilterView_desc_icon) ?: DEF_DESC_ICON
        } catch (e: Exception) {
        }
        arr.recycle()
    }

    data class FilterParams(
        var filterName: String,
        var ascIcon: String,
        var descIcon: String,
    ) {
        companion object {
            val default
                get() = FilterParams("", DEF_ASC_ICON, DEF_DESC_ICON)
        }
    }

    companion object {
        const val DEF_ASC_ICON = "\uf062"
        const val DEF_DESC_ICON = "\uf063"
    }
}