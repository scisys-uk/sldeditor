/*
 *    SLDEditor - SLD Editor application
 *
 *    (C) 2016, SCISYS
 *
 */

package com.sldeditor.test.unit.ui.detail.config.colourmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sldeditor.common.undo.UndoEvent;
import com.sldeditor.common.undo.UndoManager;
import com.sldeditor.common.xml.ui.FieldIdEnum;
import com.sldeditor.ui.detail.config.FieldConfigBase;
import com.sldeditor.ui.detail.config.FieldConfigCommonData;
import com.sldeditor.ui.detail.config.FieldConfigPopulate;
import com.sldeditor.ui.detail.config.colourmap.EncodeColourMap;
import com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.styling.ColorMap;
import org.geotools.styling.ColorMapEntryImpl;
import org.geotools.styling.ColorMapImpl;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.opengis.filter.FilterFactory;

/**
 * The unit test for FieldConfigColourMap.
 *
 * <p>{@link com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap}
 *
 * @author Robert Ward (SCISYS)
 */
public class FieldConfigColourMapTest {

    /** The Class TestFieldConfigColourMap, exposes protected methods for testing. */
    class TestFieldConfigColourMap extends FieldConfigColourMap {

        /**
         * Instantiates a new test field config colour map.
         *
         * @param commonData the common data
         */
        public TestFieldConfigColourMap(FieldConfigCommonData commonData) {
            super(commonData);
        }

        /* (non-Javadoc)
         * @see com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#addEntry()
         */
        @Override
        protected void addEntry() {
            super.addEntry();
        }

        /* (non-Javadoc)
         * @see com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#removeEntry()
         */
        @Override
        protected void removeEntry() {
            super.removeEntry();
        }

        /**
         * Checks if is removes the button enabled.
         *
         * @return true, if is removes the button enabled
         */
        public boolean isRemoveButtonEnabled() {
            return removeButton.isEnabled();
        }

        /**
         * Gets the row count.
         *
         * @return the row count
         */
        public int getRowCount() {
            return model.getRowCount();
        }

        /* (non-Javadoc)
         * @see com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#itemsSelected()
         */
        @Override
        protected void itemsSelected() {
            super.itemsSelected();
        }

        /**
         * Select row.
         *
         * @param rowFrom the row from
         * @param rowTo the row to
         */
        public void selectRow(int rowFrom, int rowTo) {
            table.setRowSelectionInterval(rowFrom, rowTo);
        }

        /**
         * Call create copy.
         *
         * @param fieldConfigBase the field config base
         * @return the field config populate
         */
        public FieldConfigPopulate callCreateCopy(FieldConfigBase fieldConfigBase) {
            return createCopy(fieldConfigBase);
        }
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#internal_setEnabled(boolean)}.
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#isEnabled()}.
     */
    @Test
    public void testSetEnabled() {
        FieldConfigColourMap field =
                new FieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));

        // Field will not have been created
        boolean expectedValue = true;
        field.internal_setEnabled(expectedValue);

        assertFalse(field.isEnabled());

        // Create text field
        field.createUI();
        field.createUI();
        assertEquals(expectedValue, field.isEnabled());

        expectedValue = false;
        field.internal_setEnabled(expectedValue);

        assertEquals(expectedValue, field.isEnabled());
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#setVisible(boolean)}.
     */
    @Test
    public void testSetVisible() {
        FieldConfigColourMap field =
                new FieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));

        boolean expectedValue = true;
        field.setVisible(expectedValue);
        field.createUI();
        field.setVisible(expectedValue);

        expectedValue = false;
        field.setVisible(expectedValue);
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#generateExpression()}. Test
     * method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#populateExpression(java.lang.Object,
     * org.opengis.filter.expression.Expression)}. Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#populateField(org.geotools.styling.ColorMap)}.
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#setTestValue(com.sldeditor.ui.detail.config.FieldId,
     * org.geotools.styling.ColorMap)}. Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#getColourMap()}.
     */
    @Test
    public void testGenerateExpression() {

        FieldConfigColourMap field =
                new FieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));
        ColorMap testValue = null;
        field.populate(null);
        field.setTestValue(FieldIdEnum.UNKNOWN, testValue);
        field.populateField(testValue);

        field.createUI();

        ColorMap expectedValue1 = new ColorMapImpl();
        field.populateField(expectedValue1);
        assertEquals(expectedValue1, field.getColourMap());

        FilterFactory ff = CommonFactoryFinder.getFilterFactory();

        ColorMap expectedValue2 = new ColorMapImpl();
        ColorMapEntryImpl entry = new ColorMapEntryImpl();
        entry.setColor(ff.literal("#001122"));
        expectedValue2.addColorMapEntry(entry);
        field.setTestValue(FieldIdEnum.UNKNOWN, expectedValue2);
        assertEquals(
                expectedValue2.getColorMapEntries().length,
                field.getColourMap().getColorMapEntries().length);

        field.populateExpression((String) null);
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#revertToDefaultValue()}.
     */
    @Test
    public void testRevertToDefaultValue() {
        FieldConfigColourMap field =
                new FieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));

        field.revertToDefaultValue();
        assertNull(field.getColourMap());

        field.createUI();
        field.revertToDefaultValue();
        assertNotNull(field.getColourMap());
        assertTrue(field.getColourMap().getColorMapEntries().length == 0);
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#createCopy(com.sldeditor.ui.detail.config.FieldConfigBase)}.
     */
    @Test
    public void testCreateCopy() {

        TestFieldConfigColourMap field =
                new TestFieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));
        FieldConfigColourMap copy = (FieldConfigColourMap) field.callCreateCopy(null);
        assertNull(copy);

        copy = (FieldConfigColourMap) field.callCreateCopy(field);
        assertEquals(field.getFieldId(), copy.getFieldId());
        assertTrue(field.getLabel().compareTo(copy.getLabel()) == 0);
        assertEquals(field.isValueOnly(), copy.isValueOnly());
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#attributeSelection(java.lang.String)}.
     */
    @Test
    public void testAttributeSelection() {
        FieldConfigColourMap field =
                new FieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));
        field.attributeSelection(null);

        // Does nothing
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#undoAction(com.sldeditor.common.undo.UndoInterface)}.
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#redoAction(com.sldeditor.common.undo.UndoInterface)}.
     */
    @Test
    public void testUndoAction() {
        FieldConfigColourMap field =
                new FieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));
        field.undoAction(null);
        field.redoAction(null);
        field.createUI();

        ColorMap expectedValue1 = new ColorMapImpl();
        field.populateField(expectedValue1);
        assertEquals(expectedValue1, field.getColourMap());

        FilterFactory ff = CommonFactoryFinder.getFilterFactory();

        ColorMap expectedValue2 = new ColorMapImpl();
        ColorMapEntryImpl entry = new ColorMapEntryImpl();
        entry.setColor(ff.literal("#001122"));
        expectedValue2.addColorMapEntry(entry);
        field.populateField(expectedValue2);

        UndoManager.getInstance().undo();
        assertEquals(
                expectedValue1.getColorMapEntries().length,
                field.getColourMap().getColorMapEntries().length);
        UndoManager.getInstance().redo();
        assertEquals(
                expectedValue2.getColorMapEntries().length,
                field.getColourMap().getColorMapEntries().length);

        // Increase the code coverage
        field.undoAction(null);
        field.undoAction(new UndoEvent(null, FieldIdEnum.NAME, "", "new"));
        field.redoAction(null);
        field.redoAction(new UndoEvent(null, FieldIdEnum.NAME, "", "new"));
    }

    /** Test undo action suppressed. */
    @Test
    public void testUndoActionSuppressed() {
        // Suppress undo events
        TestFieldConfigColourMap field =
                new TestFieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, true));
        field.createUI();

        ColorMap expectedValue1 = new ColorMapImpl();
        field.populateField(expectedValue1);
        assertEquals(expectedValue1, field.getColourMap());

        FilterFactory ff = CommonFactoryFinder.getFilterFactory();

        ColorMap expectedValue2 = new ColorMapImpl();
        ColorMapEntryImpl entry = new ColorMapEntryImpl();
        entry.setColor(ff.literal("#001122"));
        expectedValue2.addColorMapEntry(entry);

        int undoSizeList = UndoManager.getInstance().getUndoListSize();
        field.populateField(expectedValue2);

        field.addEntry();
        field.selectRow(0, 0);
        field.removeEntry();

        assertEquals(undoSizeList, UndoManager.getInstance().getUndoListSize());
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#getStringValue()}.
     */
    @Test
    public void testGetStringValue() {

        FilterFactory ff = CommonFactoryFinder.getFilterFactory();

        ColorMapEntryImpl entry1 = new ColorMapEntryImpl();
        entry1.setColor(ff.literal("#001122"));
        entry1.setLabel("testlabel");
        entry1.setOpacity(ff.literal(0.42));
        entry1.setQuantity(ff.literal(42));
        ColorMap expectedValue = new ColorMapImpl();
        expectedValue.addColorMapEntry(entry1);
        ColorMapEntryImpl entry2 = new ColorMapEntryImpl();
        entry2.setColor(ff.literal("#551122"));
        entry2.setLabel("testlabel2");
        entry2.setOpacity(ff.literal(0.22));
        entry2.setQuantity(ff.literal(12));
        expectedValue.addColorMapEntry(entry2);

        FieldConfigColourMap field =
                new FieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));
        field.populateField(expectedValue);

        assertTrue(field.getStringValue().compareTo(EncodeColourMap.encode(expectedValue)) == 0);
    }

    /**
     * Test method for {@link
     * com.sldeditor.ui.detail.config.colourmap.FieldConfigColourMap#colourMapUpdated()}.
     */
    @Test
    public void testColourMapUpdated() {
        FieldConfigColourMap field =
                new FieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));

        field.createUI();
        field.colourMapUpdated();
    }

    /** Test add entry. */
    @Test
    public void testAddEntry() {

        TestFieldConfigColourMap field =
                new TestFieldConfigColourMap(
                        new FieldConfigCommonData(
                                Geometry.class, FieldIdEnum.NAME, "label", true, false));

        field.createUI();
        assertFalse(field.isRemoveButtonEnabled());
        assertEquals(0, field.getRowCount());

        // Add entry
        field.addEntry();
        assertEquals(1, field.getRowCount());
        assertFalse(field.isRemoveButtonEnabled());

        // Remove row
        field.selectRow(0, 0);
        assertTrue(field.isRemoveButtonEnabled());
        field.removeEntry();
        assertEquals(0, field.getRowCount());
        assertFalse(field.isRemoveButtonEnabled());

        // Add multiple rows
        field.addEntry();
        field.addEntry();
        field.addEntry();
        field.addEntry();
        field.addEntry();
        assertEquals(5, field.getRowCount());

        // Remove 3 row
        field.selectRow(1, 3);
        assertTrue(field.isRemoveButtonEnabled());
        field.removeEntry();
        assertEquals(2, field.getRowCount());
        assertFalse(field.isRemoveButtonEnabled());

        // Check colour map entry
        field.selectRow(0, 0);
        field.colourMapEntryUpdated(null);
        assertFalse(field.isRemoveButtonEnabled());
    }
}
