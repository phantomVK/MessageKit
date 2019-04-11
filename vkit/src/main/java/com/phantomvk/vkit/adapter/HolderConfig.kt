package com.phantomvk.vkit.adapter

import android.view.View
import androidx.annotation.LayoutRes
import com.phantomvk.vkit.adapter.holder.AbstractViewHolder

class HolderConfig constructor(@LayoutRes val layoutId: Int,
                               val holder: (View) -> AbstractViewHolder,
                               val maxRecycledViews: Int = 10,
                               val uniqueViewId: Boolean = false)