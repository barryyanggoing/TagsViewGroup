package com.lrts.me.barryyangdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.List;

/**
 * @desc:
 * @author: BarryYang
 * @date:create on 2018/8/31 11:46
 */
public class MyAdapter extends BaseAdapter {

    private List<TagsGroup> list;

    public MyAdapter(List<TagsGroup> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TagsGroup getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TagsGroup tagsGroup = list.get(position);
        viewHolder.classifyFilterView.setData(tagsGroup.getList());
        viewHolder.classifyFilterView.setOnTagClickListener(new ClassifyFilterView.OnTagClickListener() {
            @Override
            public void tagClick(Category category) {
                Toast.makeText(parent.getContext(), category.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder {
        private ClassifyFilterView classifyFilterView;
        public ViewHolder(View view) {
            classifyFilterView = view.findViewById(R.id.layout_group);

        }
    }
}
