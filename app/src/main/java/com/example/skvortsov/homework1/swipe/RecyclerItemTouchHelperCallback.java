package com.example.skvortsov.homework1.swipe;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.skvortsov.homework1.EventAdapter;

// отвечает за движение карточки, анимация
public class RecyclerItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {
    private  RecyclerItemTouchHelperListener listener;

    public  interface RecyclerItemTouchHelperListener{
        void onSwiped (RecyclerView.ViewHolder viewHolder, int direction, int position);
    }

    public RecyclerItemTouchHelperCallback(RecyclerItemTouchHelperListener  recyclerItemTouchHelperListener, int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
        this.listener = recyclerItemTouchHelperListener;
    }

    @Override
    // касание не пойдет дальше
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (viewHolder!=null) {
            listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View foreground = ((EventAdapter.EventViewHolder) viewHolder).getForegraund();
        getDefaultUIUtil().onDraw(c,recyclerView,foreground,dX,dY,actionState,isCurrentlyActive);

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
     //   super.onSelectedChanged(viewHolder, actionState);
        if (viewHolder != null) {
            View foreground = ((EventAdapter.EventViewHolder) viewHolder).getForegraund();
            getDefaultUIUtil().onSelected(foreground);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        super.clearView(recyclerView, viewHolder);
        View foreground = ((EventAdapter.EventViewHolder) viewHolder).getForegraund();
        getDefaultUIUtil().clearView(foreground);
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View foreground = ((EventAdapter.EventViewHolder) viewHolder).getForegraund();
        getDefaultUIUtil().onDrawOver(c, recyclerView,foreground,dX, dY,actionState, isCurrentlyActive);

    }

}
