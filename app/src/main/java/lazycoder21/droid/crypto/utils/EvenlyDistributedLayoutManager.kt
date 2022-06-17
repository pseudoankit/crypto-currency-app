package lazycoder21.droid.crypto.utils

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EvenlyDistributedLayoutManager : LinearLayoutManager {
    constructor(context: Context?) : super(context)
    constructor(
        context: Context?, orientation: Int, reverseLayout: Boolean,
    ) : super(context, orientation, reverseLayout)

    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateDefaultLayoutParams())
    }

    override fun generateLayoutParams(c: Context, attrs: AttributeSet): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateLayoutParams(c, attrs))
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateLayoutParams(lp))
    }

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
        return super.checkLayoutParams(lp)
    }

    private fun spanLayoutSize(layoutParams: RecyclerView.LayoutParams): RecyclerView.LayoutParams {
        if (orientation == HORIZONTAL) {
            layoutParams.width = Math.round(horizontalSpace / itemCount.toDouble()).toInt()
        } else if (orientation == VERTICAL) {
            layoutParams.height = Math.round(verticalSpace / itemCount.toDouble()).toInt()
        }
        return layoutParams
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    private val horizontalSpace: Int
        get() = width - paddingRight - paddingLeft
    private val verticalSpace: Int
        get() = height - paddingBottom - paddingTop
}