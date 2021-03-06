/*
 * SLD Editor - The Open Source Java SLD Editor
 *
 * Copyright (C) 2016, SCISYS UK Limited
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

package com.sldeditor.tool.stickDataSource;

import com.sldeditor.common.NodeInterface;
import com.sldeditor.common.SLDDataInterface;
import com.sldeditor.common.localisation.Localisation;
import com.sldeditor.datasource.SLDEditorFile;
import com.sldeditor.datasource.StickyDataSourceInterface;
import com.sldeditor.tool.ToggleToolButton;
import com.sldeditor.tool.ToolInterface;
import com.sldeditor.tool.ToolPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The Class StickyDataSourceTool.
 *
 * @author Robert Ward (SCISYS)
 */
public class StickyDataSourceTool implements ToolInterface, StickyDataSourceInterface {

    /** The Constant PANEL_WIDTH. */
    private static final int PANEL_WIDTH = 60;

    /** The sticky button. */
    private ToggleToolButton stickyButton;

    /** The group panel. */
    private JPanel groupPanel = null;

    /** Instantiates a new sticky data source tool. */
    public StickyDataSourceTool() {
        createUI();
        SLDEditorFile.getInstance().addStickyDataSourceListener(this);
    }

    /** Creates the UI. */
    private void createUI() {
        groupPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) groupPanel.getLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        groupPanel.setBorder(
                BorderFactory.createTitledBorder(
                        Localisation.getString(
                                StickyDataSourceTool.class, "StickyDataSourceTool.groupTitle")));

        // Export to YSLD
        stickyButton =
                new ToggleToolButton(
                        Localisation.getString(
                                StickyDataSourceTool.class, "StickyDataSourceTool.dataSource"),
                        "tool/stickydatasource.png");
        stickyButton.setEnabled(true);
        final StickyDataSourceTool callingObj = this;

        stickyButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        SLDEditorFile.getInstance()
                                .setStickyDataSource(callingObj, stickyButton.isSelected());
                    }
                });

        groupPanel.add(stickyButton);
        groupPanel.setPreferredSize(new Dimension(PANEL_WIDTH, ToolPanel.TOOL_PANEL_HEIGHT));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sldeditor.tool.ToolInterface#getPanel()
     */
    @Override
    public JPanel getPanel() {
        return groupPanel;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sldeditor.tool.ToolInterface#setSelectedItems(java.util.List, java.util.List)
     */
    @Override
    public void setSelectedItems(
            List<NodeInterface> nodeTypeList, List<SLDDataInterface> sldDataList) {}

    /*
     * (non-Javadoc)
     *
     * @see com.sldeditor.tool.ToolInterface#getToolName()
     */
    @Override
    public String getToolName() {
        return getClass().getName();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sldeditor.tool.ToolInterface#supports(java.util.List, java.util.List, java.util.List)
     */
    @Override
    public boolean supports(
            List<Class<?>> uniqueNodeTypeList,
            List<NodeInterface> nodeTypeList,
            List<SLDDataInterface> sldDataList) {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sldeditor.datasource.StickyDataSourceInterface#stickyDataSourceUpdates(boolean)
     */
    @Override
    public void stickyDataSourceUpdates(boolean updated) {
        stickyButton.setSelected(updated);
    }
}
