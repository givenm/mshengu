/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.dussan.vaadin.dcharts.ChartImageFormat;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.DownloadButtonLocation;
import org.dussan.vaadin.dcharts.base.elements.PointLabels;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.TextAligns;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
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
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.CanvasAxisTickRenderer;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendByVehicle;

/**
 *
 * @author geek
 */
public class TotalMaintenanceSpendPerVehicleChart implements Serializable {

    public DCharts createChart(List<TotalMaintenanceSpendByVehicle> spendByVehicleChartDataList, String chartPeriod) {

        List<Object> totalList = new ArrayList<>();
        List<Object> numberPlateList = new ArrayList<>();
        //
        DataSeries dataSeries = new DataSeries();
        Axes axes = new Axes();
        XYaxis xyAxis = new XYaxis();
        Ticks ticks = new Ticks();

        // Get Objects of Data
        for (TotalMaintenanceSpendByVehicle totalMaintenanceSpendByVehicle : spendByVehicleChartDataList) {
            totalList.add(totalMaintenanceSpendByVehicle.getTotal());
            numberPlateList.add(totalMaintenanceSpendByVehicle.getNumberPlate());

        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] numberPlateListArray = numberPlateList.toArray(new Object[numberPlateList.size()]);

        // Add Objects as DataSeries and Ticks
        dataSeries.add(totalListArray);
        ticks.add(numberPlateListArray);


        xyAxis.setRenderer(AxisRenderers.CATEGORY);
        xyAxis.setTicks(ticks);

//        if (numberPlateList.size() >= 5) { // flip Tick 90 or 45 degrees. in order to Avoid congestion of Ticks
        xyAxis.setTickRenderer(TickRenderers.CANVAS);
        xyAxis.setTickOptions(
                new CanvasAxisTickRenderer()
                .setAngle(-45)
                .setFontSize("10pt")
                .setShowMark(true)
                .setShowGridline(true));
//        }
        axes.addAxis(xyAxis);
        axes.addAxis(
                new XYaxis(XYaxes.Y)
                .setPad(1.05f)
                .setMin(0)
                .setTickInterval(25000) // e.g. R 5, R10, R15 etc
                .setTickOptions(
                new AxisTickRenderer()
                .setFormatString("R %'.2f")));

        //
        SeriesDefaults seriesDefaults = new SeriesDefaults()
                .setFillToZero(true)
                .setRenderer(SeriesRenderers.BAR) //                .setPointLabels(
                //                    new PointLabels()
                //                    .setFormatString("R %'.2f")
                //                    .setShow(true)
                //                    .setLocation(PointLabelLocations.NORTH)
                //                    .setEdgeTolerance(-15)
                //                )
                ;

//        SeriesDefaults seriesDefaults = new SeriesDefaults()
//	.setRenderer(SeriesRenderers.BAR)
//	.setPointLabels(
//		new PointLabels()
//			.setShow(true)
//			.setLocation(PointLabelLocations.EAST)
//			.setEdgeTolerance(-15))
//	.setShadowAngle(135)
//	.setRendererOptions(
//		new BarRenderer()
//			.setBarDirection(BarDirections.HOTIZONTAL));


//
//        Legend legend = new Legend()
//                .setShow(true)
//                .setPlacement(LegendPlacements.OUTSIDE_GRID);

        Highlighter highlighter = new Highlighter()
                .setShow(true)
                .setShowTooltip(true)
                .setTooltipAlwaysVisible(true)
                .setKeepTooltipInsideChart(true)
                .setTooltipLocation(TooltipLocations.NORTH)
                .setTooltipAxes(TooltipAxes.XY_BAR);

        Title title = new Title("Total Maintenance Cost Spend per Vehicle: " + chartPeriod);
        title.setFontSize("13pt");
        title.setTextAlign(TextAligns.LEFT);

        Series series = new Series()
                .addSeries(
                new XYseries()
                .setLabel("Maintenance Cost Spend"));

        Options options = new Options()
                .setCaptureRightClick(true)
                .setSeriesDefaults(seriesDefaults)
                .setSeries(series)
                //                .setLegend(legend)
                //                .setTitle(title)
                .setHighlighter(highlighter)
                .setAxes(axes);


        DCharts dChart = new DCharts()
                .autoSelectDecimalAndThousandsSeparator(new Locale("za", "ZA"))
                .setDataSeries(dataSeries)
                .setOptions(options)
                .setEnableDownload(false)
                //                .setDownloadButtonLocation(DownloadButtonLocation.BOTTOM_RIGHT)
                //                .setDownloadFilename("Maintenance_Cost_Spend_Per_Vehicle_" + chartPeriod)
                //                .setChartImageFormat(ChartImageFormat.GIF)
                .show();

        dChart.setWidth("600px");
        dChart.setHeight("300px");

        return dChart;
    }
}
