package com.xu.xpopupwindowdemo.popup

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xu.xpopupwindow.XPopupWindow
import com.xu.xpopupwindowdemo.R

/**
 * Created by Xu on 2018/6/23.
 * @author Xu
 */

class MenuPopupWindow: XPopupWindow {

    val LOG = "MenuPopupWindow"
    private var rv: RecyclerView? = null
    private var adapter: CustomAdapter? = null
    private var list: List<String> = emptyList()
    private var manager: LinearLayoutManager? = null

    constructor(ctx: Context): super(ctx)

    constructor(ctx: Context, w: Int, h: Int): super(ctx, w, h)

    override fun getLayoutId(): Int {
        return R.layout.popup_menu
    }

    override fun getLayoutParentNodeId(): Int {
        return R.id.menu_parent
    }

    override fun initViews() {
        rv = findViewById(R.id.rv_menu)
    }

    override fun initData() {
        list = listOf("菜单项一", "菜单项二", "菜单项三")
        adapter = CustomAdapter(list, getContext())
        manager = LinearLayoutManager(getContext())
        rv?.addItemDecoration(DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL))
        rv?.setHasFixedSize(true)
        rv?.layoutManager = manager
        rv?.itemAnimator = DefaultItemAnimator()
        rv?.adapter = adapter
    }

    override fun startAnim(view: View): Animator? {
        var animator: ObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        animator.duration = 1500
        return animator
    }

    override fun exitAnim(view: View): Animator? {
        var animator: ObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        animator.duration = 1000
        return animator
    }

    override fun animStyle(): Int {
        return -1
    }

    class CustomAdapter(private val list: List<String>, private var context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
                ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item_menu,
                        parent, false))

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvItem?.text = list[position]
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvItem: TextView? = null

            init {
                tvItem = itemView.findViewById(R.id.rv_item_menu_text)
            }
        }
    }

}