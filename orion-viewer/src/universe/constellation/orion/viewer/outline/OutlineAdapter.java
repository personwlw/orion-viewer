/*
 * Orion Viewer - pdf, djvu, xps and cbz file viewer for android devices
 *
 * Copyright (C) 2011-2013  Michael Bogdanov & Co
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package universe.constellation.orion.viewer.outline;

import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.polidea.treeview.AbstractTreeViewAdapter;
import pl.polidea.treeview.InMemoryTreeStateManager;
import pl.polidea.treeview.TreeBuilder;
import pl.polidea.treeview.TreeNodeInfo;
import universe.constellation.orion.viewer.Common;
import universe.constellation.orion.viewer.Controller;
import universe.constellation.orion.viewer.OrionViewerActivity;
import universe.constellation.orion.viewer.R;

public class OutlineAdapter extends AbstractTreeViewAdapter<Integer> {
    private final OutlineItem items[];
    private final Controller controller;
    private final Dialog dialog;

    public OutlineAdapter(Controller controller,
                          OrionViewerActivity activity,
                          Dialog dialog,
                          InMemoryTreeStateManager<Integer> manager,
                          OutlineItem items[]) {
        super(activity, manager, 20);
        this.items = items;
        this.dialog = dialog;
        this.controller = controller;
    }

    static public void initializeTreeManager(InMemoryTreeStateManager<Integer> manager, OutlineItem[] items) {
        TreeBuilder<Integer> builder = new TreeBuilder<Integer>(manager);
        builder.sequentiallyAddNextNode(0, items[0].level);

        Common.d("OutlineAdapter:: initializeTreeManager");
        for (int i = 1; i < items.length; i++) {
            OutlineItem item_last = items[i - 1];
            OutlineItem item_cur = items[i];
            int last = i - 1;
            if (item_cur.level > item_last.level) {
                builder.addRelation(last, i);
            } else {
                builder.sequentiallyAddNextNode(i, item_cur.level);
            }
        }
        Common.d("OutlineAdapter:: initializeTreeManager -- END");
    }


    @Override
    public View getNewChildView(final TreeNodeInfo<Integer> treeNodeInfo) {
        final LinearLayout viewLayout = (LinearLayout) getActivity()
                .getLayoutInflater().inflate(R.layout.outline_entry, null);
        Common.d("OutlineAdapter:: GetChildView");
        return updateView(viewLayout, treeNodeInfo);
    }

    private String getDescription(final int id) {
        Common.d("OutlineAdapter:: GetDescription");
        return this.items[id].title;
    }

    private int getPage(final int id) {
        Common.d("OutlineAdapter:: GetDescription");
        return this.items[id].page;
    }

    @Override
    public LinearLayout updateView(final View view,
                                   final TreeNodeInfo<Integer> treeNodeInfo) {
        Common.d("OutlineAdapter:: updateView");
        final LinearLayout viewLayout = (LinearLayout) view;

        ((TextView)view.findViewById(R.id.title)).setText(getDescription(treeNodeInfo.getId()));
		((TextView)view.findViewById(R.id.page)).setText(String.valueOf(getPage(treeNodeInfo.getId()) + 1));

        return viewLayout;
    }

    @Override
    public long getItemId(final int position) {
        Common.d("OutlineAdapter:: getItemID");
        return getTreeId(position);
    }

    @Override
    public Object getItem(final int position) {
        Common.d("OutlineAdapter:: getItem");
        int id = (int) getItemId(position);
        return this.items[id];
    }

    @Override
    public void handleItemClick(final View view, final Object id) {
        Common.d("OutlineAdapter:: handleItemClick");
        final Integer longId = (Integer) id;
        final TreeNodeInfo<Integer> info = getManager().getNodeInfo(longId);
        if (false && info.isWithChildren()) {
            super.handleItemClick(view, id);
        } else {
            controller.drawPage(this.items[longId].page);
            this.dialog.dismiss();
        }
        Common.d("OutlineAdapter:: handleItemClickEnd");
    }
}
