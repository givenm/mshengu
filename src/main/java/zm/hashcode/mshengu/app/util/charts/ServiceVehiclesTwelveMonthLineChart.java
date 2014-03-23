/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.charts;

import java.io.Serializable;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.PointLabels;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.base.renderers.MarkerRenderer;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.TextAligns;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.directions.AnimationDirections;
import org.dussan.vaadin.dcharts.metadata.locations.PointLabelLocations;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.metadata.styles.MarkerStyles;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.AxesDefaults;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.options.Title;
import org.dussan.vaadin.dcharts.renderers.axis.LinearAxisRenderer;
import org.dussan.vaadin.dcharts.renderers.series.LineRenderer;
import org.dussan.vaadin.dcharts.renderers.series.animations.LineAnimation;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.CanvasAxisTickRenderer;

/**
 *
 * @author Colin
 */
public class ServiceVehiclesTwelveMonthLineChart implements Serializable {

    public DCharts buildLineChart(Object[] totalListArray, Object[] monthListArray, float tickInterval, Object minTickValue, /* Object maxTickValue, */ String chartTitle) {

        DataSeries dataSeries = new DataSeries();
        dataSeries.newSeries();
        for (int i = 0; i < totalListArray.length; i++) {
            dataSeries.add(monthListArray[i], totalListArray[i]);
        }

        Series series = new Series()
                .addSeries(
                new XYseries()
                .setLineWidth(2)
                .setMarkerOptions(
                new MarkerRenderer()
                .setStyle(MarkerStyles.FILLED_SQUARE)
                .setSize(10)));

        AxesDefaults axesDefaults = new AxesDefaults()
                .setUseSeriesColor(true)
                .setRendererOptions(
                new LinearAxisRenderer()
                .setAlignTicks(true));

        Axes axes = new Axes()
                .addAxis(
                new XYaxis(XYaxes.X)
                .setRenderer(AxisRenderers.DATE)
                .setTickOptions(
                new CanvasAxisTickRenderer()
                .setAngle(-45)
                .setFontSize("10pt")
                .setShowMark(true)
                .setShowGridline(true)) //
                .setTickOptions(
                new AxisTickRenderer()
                .setFontSize("8pt")
                .setFormatString("%b-%y"))// Aug-14 // "%b-%Y" with caps Aug-2014
                //                .setDrawMinorGridlines(true)
                .setDrawMajorTickMarks(true) // NB SKIPPING TICKS
                //                .setDrawMinorTickMarks(true)//                .setNumberTicks(monthListArray.length) //
                )
                //
                .addAxis(
                new XYaxis(XYaxes.Y)
                .setMin(minTickValue)
                //                .setMax(maxTickValue)
                .setTickInterval(tickInterval)
                .setTickOptions(
                new AxisTickRenderer()
                .setFormatString("%'.2f")) // .setFormatString("R%.2f")
                );

        Highlighter highlighter = new Highlighter()
                .setShow(true)
                //                .setShowTooltip(true)
                //                .setTooltipAlwaysVisible(true)
                //                .setKeepTooltipInsideChart(true)
                .setSizeAdjust(10)
                .setTooltipLocation(TooltipLocations.NORTH)
                .setTooltipAxes(TooltipAxes.XY) //  XY Shows both axes value in the Tooltip. try XY_BAR
                //                .setTooltipFormatString("<b><i><span style='color:red;'>Value:</span></i></b> %.2f")
                //                .setUseAxesFormatters(false)
                ;

//        Cursor cursor = new Cursor()
//                .setShow(true);

        Title title = new Title("");
        title.setFontSize("13pt");
        title.setTextAlign(TextAligns.LEFT);
        title.setShow(true);

        if (chartTitle != null) {
            title.setText(chartTitle);
        }

        LineAnimation lineAnimation = new LineAnimation();
        lineAnimation.setDirection(AnimationDirections.DOWN);

        SeriesDefaults seriesDefaults = new SeriesDefaults();
        seriesDefaults.setRenderer(SeriesRenderers.LINE)
                .setPointLabels(
                new PointLabels()
                .setFormatString("%'.2f") //  .setFormatString("R%'.2f") Currency Symbol, thousand Seperator
                .setShow(true)
                .setLocation(PointLabelLocations.NORTH)
                .setEdgeTolerance(-15))
                .setShadowAngle(135)
                //                .setColor("red")
                .setRendererOptions(
                new LineRenderer()
                .setAnimation(lineAnimation));



        Options options = new Options()
                //                .addOption(seriesDefaults)
                .setTitle(title)
                .setSeries(series)
                .setAxesDefaults(axesDefaults)
                .addOption(axes)
                .addOption(highlighter) //                .addOption(cursor)
                ;

        DCharts chart = new DCharts()
                .setDataSeries(dataSeries)
                .setOptions(options) //	.show()
                ;
        return chart;
    }
}
