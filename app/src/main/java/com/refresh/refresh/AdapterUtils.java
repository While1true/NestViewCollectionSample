package com.refresh.refresh;

import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.nestrefreshlib.Adpater.Base.Holder;
import com.nestrefreshlib.Adpater.Impliment.BaseHolder;
import com.nestrefreshlib.Adpater.Impliment.PositionHolder;
import com.nestrefreshlib.Adpater.Impliment.SAdapter;
import com.nestrefreshlib.RefreshViews.AdapterHelper.StateAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class AdapterUtils {
    public static SAdapter produceAdapter(int size) {
        SAdapter adapter = new SAdapter(size)
                .addType(R.layout.item1, new PositionHolder() {
                    @Override
                    public void onBind(Holder holder, int i) {
                        holder.setText(R.id.textView, "第" + i + "个item");
                    }

                    @Override
                    public boolean istype(int position) {
                        return position % 3 == 0;
                    }
                }).addType(R.layout.item2, new PositionHolder() {
                    @Override
                    public void onBind(Holder holder, int i) {
                        holder.setText(R.id.textView, "第" + i + "个item");
                    }

                    @Override
                    public boolean istype(int i) {
                        return true;
                    }
                });
        return adapter;
    }

    public static StateAdapter produceStateAdapter(int size) {
        StateAdapter adapter = new StateAdapter(size)
                .addType(R.layout.item1, new PositionHolder() {
                    @Override
                    public void onBind(Holder holder, int i) {
                        holder.setText(R.id.textView, "第" + i + "个item");
                    }

                    @Override
                    public boolean istype(int position) {
                        return position % 3 == 0;
                    }
                }).addType(R.layout.item2, new PositionHolder() {
                    @Override
                    public void onBind(Holder holder, int i) {
                        holder.setText(R.id.textView, "第" + i + "个item");
                    }

                    @Override
                    public boolean istype(int i) {
                        return true;
                    }
                });
        return adapter;
    }

    public static StateAdapter produceFolderAdapter() {
        final List<FolderObj> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Parent parent = new Parent();
            parent.setName("一级"+i+"---position");
            for (int i1 = 0; i1 < 8; i1++) {
                Child child = new Child();
                child.setName("一级"+i+"---二级---"+i1+"---position");
                for (int i2 = 0; i2 < 20; i2++) {
                    Child_Child child_child = new Child_Child();
                    child_child.setName(child_child.getName()+"一级"+i+"---二级---"+i1+"---三级---"+i2+"---position");
                    child.getChilds().add(child_child);
                }
                parent.getChilds().add(child);
            }
            list.add(parent);

        }

        final StateAdapter adapter = new StateAdapter(list);
        adapter.addType(R.layout.item1, new BaseHolder<Parent>() {
            @Override
            public void onViewBind(final Holder holder, final Parent parent, final int i) {
                holder.setText(R.id.textView, parent.getName() + " " + i);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (parent.isIsopen()) {
                            parent.setIsopen(false);
                            for (Child child : parent.getChilds()) {
                                if (child.isIsopen()) {
                                    child.setIsopen(false);
                                    list.removeAll(child.getChilds());
                                }
                            }
                            list.removeAll(parent.getChilds());
                            adapter.notifyDataSetChanged();
                        } else {
                            parent.setIsopen(true);
                            list.addAll(i + 1, parent.getChilds());
                            adapter.notifyItemRangeInserted(i + 1, parent.getChilds().size());
                        }
                    }
                });
            }
        }).addType(R.layout.item2, new BaseHolder<Child>() {
            @Override
            public void onViewBind(Holder holder, final Child child, final int i) {
                holder.setText(R.id.textView, child.getName() + " " + i);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (child.isIsopen()) {
                            child.setIsopen(false);
                            list.removeAll(child.getChilds());
                            adapter.notifyItemRangeRemoved(i + 1, child.getChilds().size());
                        } else {
                            child.setIsopen(true);
                            list.addAll(i + 1, child.getChilds());
                            adapter.notifyItemRangeInserted(i + 1, child.getChilds().size());
                        }
                    }
                });
            }
        }).addType(R.layout.item3, new BaseHolder<Child_Child>() {
            @Override
            public void onViewBind(Holder holder, Child_Child child_child, final int i) {
                holder.setText(R.id.textView, child_child.getName() + " " + i);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),i+" 被点击了",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return adapter;
    }

    private static class Parent implements FolderObj {
        boolean isopen = false;
        String name;
        @Override
        public int getType() {
            return 0;
        }

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsopen() {
            return isopen;
        }

        public void setIsopen(boolean isopen) {
            this.isopen = isopen;
        }

        List<Child> childs = new ArrayList<>();

        public List<Child> getChilds() {
            return childs;
        }

        public void setChilds(List<Child> childs) {
            this.childs = childs;
        }
    }

    private static class Child implements FolderObj {
        List<Child_Child> childs = new ArrayList<>();
        boolean isopen = false;
        String name;
        public boolean isIsopen() {
            return isopen;
        }

        public void setIsopen(boolean isopen) {
            this.isopen = isopen;
        }

        public List<Child_Child> getChilds() {
            return childs;
        }

        public void setChilds(List<Child_Child> childs) {
            this.childs = childs;
        }

        @Override
        public int getType() {
            return 1;
        }

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    private static class Child_Child implements FolderObj {
        private static String[] names = {"xxx", "machel", "doucnj", "ddsdwww", "sdsef", "aqweqww", "wfthfggf"};
        String name = names[new Random().nextInt(names.length)];

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int getType() {
            return 2;
        }

    }

    interface FolderObj {
        int getType();

        String getName();
    }
}
