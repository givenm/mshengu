/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.charts;

import java.io.Serializable;
import java.util.Locale;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.TextAligns;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.TickRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.options.Title;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.CanvasAxisTickRenderer;

/**
 *
 * @author Colin
 */
public class BarChart implements Serializable {

    public DCharts buildBarChart(Object[] totalListArray, Object[] nameListArray, float tickInterval, String label, Object minTickValue) {

        DataSeries dataSeries = new DataSeries();
        Series series = new Series();
        Axes axes = new Axes();
        XYaxis xyAxis = new XYaxis();
        Ticks ticks = new Ticks();
        // Add Objects as DataSeries and Ticks
        dataSeries.add(totalListArray);
        ticks.add(nameListArray);

        Highlighter highlighter = new Highlighter()
                .setShow(true)
                .setShowTooltip(true)
                .setTooltipAlwaysVisible(true)
                .setKeepTooltipInsideChart(true)
                .setTooltipLocation(TooltipLocations.NORTH) //                .setTooltipAxes(TooltipAxes.XY_BAR) // NB FLIP the display in the tooltip
                ;

        series.addSeries(
                new XYseries().setLabel(label));

        highlighter.setTooltipAxes(TooltipAxes.XY_BAR); // NB FLIP the display in the tooltip e.g. TooltipAxes.XY_BAR

        xyAxis.setTicks(ticks);
        xyAxis.setRenderer(AxisRenderers.CATEGORY);
        xyAxis.setTickRenderer(TickRenderers.CANVAS);

        if (nameListArray.length >= 9) { // flip Tick 90 degrees. Avoid congestion
//            xyAxis.setTickRenderer(TickRenderers.CANVAS);
            xyAxis.setTickOptions(
                    new CanvasAxisTickRenderer()
                    .setAngle(-45)
                    .setFontSize("10pt")
                    .setShowMark(true)
                    .setShowGridline(true));
        }

        xyAxis.setTickOptions(
                new CanvasAxisTickRenderer()
                .setAngle(-45)
                .setFontSize("10pt")
                .setShowMark(true)
                .setShowGridline(true));
        axes.addAxis(xyAxis);
        axes.addAxis(
                new XYaxis(XYaxes.Y)
                .setPad(1.05f)
                .setMin(minTickValue)
                .setTickInterval(tickInterval)
                .setTickOptions(
                new AxisTickRenderer().setFormatString("R %.2f")));

        //
        SeriesDefaults seriesDefaults = new SeriesDefaults();
        seriesDefaults.setFillToZero(true)
                .setRenderer(SeriesRenderers.BAR);

        Legend legend = new Legend()
                .setShow(true)
                .setPlacement(LegendPlacements.OUTSIDE_GRID);

        Title title = new Title("");
        title.setFontSize("13pt");
        title.setTextAlign(TextAligns.LEFT);

        Options options = new Options()
                .setCaptureRightClick(true)
                .setSeriesDefaults(seriesDefaults)
                .setSeries(series)
                //                .setLegend(legend)
                .setTitle(title)
                .setHighlighter(highlighter)
                .setAxes(axes);

        DCharts dChart = new DCharts()
                .autoSelectDecimalAndThousandsSeparator(new Locale("za", "ZA"))
                .setDataSeries(dataSeries)
                .setOptions(options)
                .setEnableDownload(false)
                //                .setDownloadButtonLocation(DownloadButtonLocation.BOTTOM_RIGHT)
                //                .setDownloadButtonCaption("Save Chart")
                //                .setDownloadFilename("Maintenance_Spend_Km_Travelled_" + chartPeriod)
                //                .setChartImageFormat(ChartImageFormat.GIF)
                .setMarginBottom(10)
                .setMarginLeft(10)
                .setMarginRight(10);

        return dChart;
    }
}
