package com.phantomvk.messagekit.tools

import androidx.recyclerview.widget.RecyclerView

/**
 * This is a single instance of MessageViewPool.
 *
 * After the old activity using this pool was destroyed, all available
 * ViewHolders cached in this pool will not be recycled.
 *
 * When the same new activity class starts again, the new adapter instance
 * should set this pool as default instead of creating a new one.
 *
 * No new ViewHolder instance will create unless the specific type of the
 * view was not cached in the pool, or no enough cached instance to reuse.
 *
 * As a result, this pool uses some memory but largely reduce the frequency
 * of views' inflation, improve speed when fetching views. So UI will be
 * showed to the users quicker then the situation without this pool.
 *
 * It is simple but useful to improve the user experience.
 */
object MessageViewPool : RecyclerView.RecycledViewPool()