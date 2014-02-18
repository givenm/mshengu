/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.PointLabels;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.TextAligns;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.directions.BarDirections;
import org.dussan.vaadin.dcharts.metadata.locations.PointLabelLocations;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.TickRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.options.Title;
import org.dussan.vaadin.dcharts.renderers.series.BarRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.CanvasAxisTickRenderer;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendBySupplier;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;

/**
 *
 * @author Colin
 */
public class MaintenanceSpendBySupplierBarChart implements Serializable {

    public DCharts createChart(List<TotalMaintenanceSpendBySupplier> totalMaintenanceSpendBySupplierList, String chartPeriod /* , String chartType */) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
        float tickInterval = Float.parseFloat("100000");
        BigDecimal highestTotal = BigDecimal.ZERO;
        for (TotalMaintenanceSpendBySupplier totalMaintenanceSpendBySupplier : totalMaintenanceSpendBySupplierList) {
            if (totalMaintenanceSpendBySupplier.getTotal().compareTo(highestTotal) > 0) {
                highestTotal = totalMaintenanceSpendBySupplier.getTotal();
            }
        }

        if (highestTotal.compareTo(new BigDecimal("300000")) < 0) {
            tickInterval = Float.parseFloat("50000");
        } else if (highestTotal.compareTo(new BigDecimal("600000")) < 0) {
            tickInterval = Float.parseFloat("100000");
        }


        List<Object> totalList = new ArrayList<>();
        List<Object> serviceProviderNameList = new ArrayList<>();
        //
        DataSeries dataSeries = new DataSeries();
        Series series = new Series();
        Axes axes = new Axes();
        XYaxis xyAxis = new XYaxis();
        Ticks ticks = new Ticks();

        // Get Objects of Data
        for (TotalMaintenanceSpendBySupplier totalMaintenanceSpendBySupplier : totalMaintenanceSpendBySupplierList) {
            totalList.add(totalMaintenanceSpendBySupplier.getTotal());
            serviceProviderNameList.add(totalMaintenanceSpendBySupplier.getSupplierName());
            // Add Percentage for PIE
        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] serviceProviderNameListArray = serviceProviderNameList.toArray(new Object[serviceProviderNameList.size()]);

        // Add Objects as DataSeries and Ticks
        dataSeries.add(totalListArray);
        ticks.add(serviceProviderNameListArray);

        Highlighter highlighter = new Highlighter()
                .setShow(true)
                .setShowTooltip(true)
                .setTooltipAlwaysVisible(true)
                .setKeepTooltipInsideChart(true)
                .setTooltipLocation(TooltipLocations.NORTH) //                .setTooltipAxes(TooltipAxes.XY_BAR) // NB FLIP the display in the tooltip
                ;


////        if (chartType.equalsIgnoreCase("dashboard")) {
        series.addSeries(
                new XYseries().setLabel("Spend/Km Travelled"));

        highlighter.setTooltipAxes(TooltipAxes.XY_BAR); // NB FLIP the display in the tooltip e.g. TooltipAxes.XY_BAR

//        if (numberPlateList.size() >= 9) { // flip Tick 90 degrees. Avoid congestion
        xyAxis.setTicks(ticks);
        xyAxis.setRenderer(AxisRenderers.CATEGORY);
        xyAxis.setTickRenderer(TickRenderers.CANVAS);
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
                .setMin(0)
                .setTickInterval(tickInterval)
                .setTickOptions(
                new AxisTickRenderer().setFormatString("R %.2f")));
//////        }
////        }
////
////        else {
////            series.addSeries(
////                    new XYseries().setLabel("Maintenance spend by Km Travelled"));
////
////            highlighter.setTooltipAxes(TooltipAxes.YX_BAR); // NB FLIP the display in the tooltip e.g. TooltipAxes.YX_BAR
////
//////            xyAxis.setTickOptions(
//////                    //                    new CanvasAxisTickRenderer()
//////                    //                    .setAngle(-45)
//////                    //                    .setFontSize("10pt")
//////                    //                    .setShowMark(true)
//////                    //                    .setShowGridline(true)
//////                    new AxisTickRenderer()
//////                    .setFormatString("R %.2f"));
//////            axes.addAxis(xyAxis);
////            axes.addAxis(
////                    new XYaxis(XYaxes.Y)
////                    .setTicks(ticks)
////                    .setTickRenderer(TickRenderers.CANVAS)
////                    .setRenderer(AxisRenderers.CATEGORY));
////
////            axes.addAxis(
////                    new XYaxis(XYaxes.X)
////                    .setPad(1.05f)
////                    .setMin(0)
////                    .setTickInterval(tickInterval)
////                    .setTickOptions(
////                    new AxisTickRenderer().setFormatString("R %.2f"))); // 	.setFormatString("$%d") // .setFormatString("R %.1f") // .setFormatString("R %.0f")
////        }

        //
        SeriesDefaults seriesDefaults = new SeriesDefaults();
////        if (chartType.equalsIgnoreCase("dashboard")) {
        seriesDefaults.setFillToZero(true)
                .setRenderer(SeriesRenderers.BAR);
////        } else {
////
////            seriesDefaults.setRenderer(SeriesRenderers.BAR)
////                    .setPointLabels(
////                    new PointLabels()
////                    .setFormatString("R %'.2f")
////                    .setShow(true)
////                    .setLocation(PointLabelLocations.EAST)
////                    .setEdgeTolerance(-15))
////                    .setShadowAngle(135)
////                    .setRendererOptions(
////                    new BarRenderer()
////                    .setBarDirection(BarDirections.HOTIZONTAL));
////        }

//        Legend legend = new Legend()
//                .setShow(true)
//                .setPlacement(LegendPlacements.OUTSIDE_GRID);

        Title title = new Title("Total Fleet Maintenance Spend by Supplier: " + chartPeriod);
        title.setFontSize("13pt");
        title.setTextAlign(TextAligns.LEFT);

        Options options = new Options()
                .setCaptureRightClick(true)
                .setSeriesDefaults(seriesDefaults)
                .setSeries(series)
                //                .setLegend(legend)
                .setHighlighter(highlighter)
                .setAxes(axes);

////        if (chartType.equalsIgnoreCase("dashboard")) {
//////            options.setTitle(title);
////        }

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
                .setMarginRight(10)
                .show();
////        if (!chartType.equalsIgnoreCase("dashboard")) {
////            dChart.setMarginTop(-3);
////        }

        dChart.setWidth("600px");
        dChart.setHeight("300px");
        return dChart;
    }
}
