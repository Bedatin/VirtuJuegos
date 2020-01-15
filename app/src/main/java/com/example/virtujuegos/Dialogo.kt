package com.example.virtujuegos

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_memory.*
import kotlinx.android.synthetic.main.fragment_dialogo.*


class Dialogo(private var info: Int) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialogo, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvInfoMemo.text = getText(info)
        bInfoMemo.setOnClickListener {
            dialog?.dismiss()
        }
        scrollView.viewTreeObserver.addOnGlobalLayoutListener {
            val scroll: Int = getScrollRange(scrollView)
            seekBar.min = 0
            seekBar.max = scroll

            seekBar.setOnSeekBarChangeListener  (object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    scrollView.scrollTo(0, progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                }
            })
        }
        seekBar.layoutParams.width = 250
    }

    private fun getScrollRange(scrollView: ScrollView): Int {

        var scrollRange = 0
        if (scrollView.childCount > 0) {
            val child = scrollView.getChildAt(0)
            scrollRange =
                Math.max(
                    0,
                    child.height - (scrollView.height - scrollView.paddingBottom - scrollView.paddingTop)
                )
        }
        return scrollRange
    }

}
