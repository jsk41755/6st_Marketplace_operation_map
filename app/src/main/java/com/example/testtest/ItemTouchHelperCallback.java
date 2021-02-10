package com.example.testtest;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtest.ItemTouchHelperListener;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {   //이것을 구현해야 itemtouchhelper를 쓸 수 있다.
    private ItemTouchHelperListener listener;

    public ItemTouchHelperCallback(ItemTouchHelperListener listener) {
        this.listener = listener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) { //어떤 방향으로 swipe와 drag를 할 것인지 결정
        int drag_flags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipe_flags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(drag_flags, swipe_flags);     //drag랑 swipe 둘 중 하나만 사용하겠다면 그 매개변수를 제외한 매개변수에만 0을 넣는다.
    }                                                          //ex)onswipe만 사용하겠다면 drag_flags에 0을 넣으면 된다.

    @Override
    public boolean isLongPressDragEnabled() {  //long press를 했을 때 drag를 지원하기 위함.
        return true;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onItemSwipe(viewHolder.getAdapterPosition());
    }
}

