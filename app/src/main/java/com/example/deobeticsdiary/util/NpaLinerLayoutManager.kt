package com.example.deobeticsdiary.util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class NpaLinerLayoutManager(context: Context?) : LinearLayoutManager(context) {

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }

}