/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.chemicals.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.chemicals.DashboardChemicalsMenu;

/**
 *
 * @author Ferox
 */
public class ChemicalsExecutiveDashboardTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;

    public ChemicalsExecutiveDashboardTab(MshenguMain app) {
        main = app;
        setSizeFull();
        DataSeries dataSeries = new DataSeries();
        dataSeries.add(200, 600, 700, 1000);
        dataSeries.add(460, -210, 690, 820);
        dataSeries.add(-260, -440, 320, 200);

        SeriesDefaults seriesDefaults = new SeriesDefaults()
                .setFillToZero(true)
                .setRenderer(SeriesRenderers.BAR);


        Series series = new Series()
                .addSeries(
                new XYseries()
                .setLabel("Hotel"))
                .addSeries(
                new XYseries()
                .setLabel("Event Regristration"))
                .addSeries(
                new XYseries()
                .setLabel("Airfare"));

        Legend legend = new Legend()
                .setShow(true)
                .setPlacement(LegendPlacements.OUTSIDE_GRID);

        Highlighter highlighter = new Highlighter()
                .setShow(true)
                .setShowTooltip(true)
                .setTooltipAlwaysVisible(true)
                .setKeepTooltipInsideChart(true)
                .setTooltipLocation(TooltipLocations.NORTH)
                .setTooltipAxes(TooltipAxes.XY_BAR);

        Axes axes = new Axes()
                .addAxis(
                new XYaxis()
                .setRenderer(AxisRenderers.CATEGORY)
                .setTicks(
                new Ticks()
                .add("May", "June", "July", "August")))
                .addAxis(
                new XYaxis(XYaxes.Y)
                .setPad(1.05f)
                .setTickOptions(
                new AxisTickRenderer()
                .setFormatString("$%d")));

        Options options = new Options()
                .setSeriesDefaults(seriesDefaults)
                .setSeries(series)
                .setLegend(legend)
                .setHighlighter(highlighter)
                .setAxes(axes);

        DCharts chart = new DCharts()
                .setDataSeries(dataSeries)
                .setOptions(options)
                .show();
        addComponent(chart);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
    }

    private void getHome() {
        main.content.setSecondComponent(new DashboardChemicalsMenu(main, "EXECUTIVE"));
    }
}
