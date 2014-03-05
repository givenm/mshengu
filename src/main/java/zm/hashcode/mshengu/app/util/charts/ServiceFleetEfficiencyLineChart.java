/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.charts;

import java.io.Serializable;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.Trendline;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.base.renderers.MarkerRenderer;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.TextAligns;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.LabelRenderers;
import org.dussan.vaadin.dcharts.metadata.styles.MarkerStyles;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.AxesDefaults;
import org.dussan.vaadin.dcharts.options.Cursor;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.options.Title;
import org.dussan.vaadin.dcharts.renderers.axis.LinearAxisRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.CanvasAxisTickRenderer;

/**
 *
 * @author Colin
 *
 */

/*
 FOUND @ http://www.jqplot.com/docs/files/plugins/jqplot-dateAxisRenderer-js.html
 Dates can be passed into the axis in almost any recognizable value and will be parsed.  They will be rendered on the axis in the format specified by tickOptions.formatString.  e.g. tickOptions.formatString = ‘%Y-%m-%d’.

 Accecptable format codes are:

 Code    Result                  Description
 == Years ==
 %Y      2008                Four-digit year
 %y      08                  Two-digit year
 == Months ==
 %m      09                  Two-digit month
 %#m     9                   One or two-digit month
 %B      September           Full month name
 %b      Sep                 Abbreviated month name
 == Days ==
 %d      05                  Two-digit day of month
 %#d     5                   One or two-digit day of month
 %e      5                   One or two-digit day of month
 %A      Sunday              Full name of the day of the week
 %a      Sun                 Abbreviated name of the day of the week
 %w      0                   Number of the day of the week (0 = Sunday, 6 = Saturday)
 %o      th                  The ordinal suffix string following the day of the month
 == Hours ==
 %H      23                  Hours in 24-hour format (two digits)
 %#H     3                   Hours in 24-hour integer format (one or two digits)
 %I      11                  Hours in 12-hour format (two digits)
 %#I     3                   Hours in 12-hour integer format (one or two digits)
 %p      PM                  AM or PM
 == Minutes ==
 %M      09                  Minutes (two digits)
 %#M     9                   Minutes (one or two digits)
 == Seconds ==
 %S      02                  Seconds (two digits)
 %#S     2                   Seconds (one or two digits)
 %s      1206567625723       Unix timestamp (Seconds past 1970-01-01 00:00:00)
 == Milliseconds ==
 %N      008                 Milliseconds (three digits)
 %#N     8                   Milliseconds (one to three digits)
 == Timezone ==
 %O      360                 difference in minutes between local time and GMT
 %Z      Mountain Standard Time  Name of timezone as reported by browser
 %G      -06:00              Hours and minutes between GMT
 == Shortcuts ==
 %F      2008-03-26          %Y-%m-%d
 %T      05:06:30            %H:%M:%S
 %X      05:06:30            %H:%M:%S
 %x      03/26/08            %m/%d/%y
 %D      03/26/08            %m/%d/%y
 %#c     Wed Mar 26 15:31:00 2008  %a %b %e %H:%M:%S %Y
 %v      3-Sep-2008          %e-%b-%Y
 %R      15:31               %H:%M
 %r      3:31:00 PM          %I:%M:%S %p
 == Characters ==
 %n      \n                  Newline
 %t      \t                  Tab
 %%      %                   Percent Symbol
 */
public class ServiceFleetEfficiencyLineChart implements Serializable {

    public DCharts buildLineChart(Object[] totalListArray, Object[] monthListArray, float tickInterval, Object minTickValue, String chartTitle) {

        DataSeries dataSeries = new DataSeries();
        dataSeries.newSeries();
        for (int i = 0; i < totalListArray.length; i++) {
            dataSeries.add(monthListArray[i], totalListArray[i]);
        }

        Series series = new Series()
                .addSeries(
                new XYseries()
                .setLineWidth(5)
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
                .setFormatString("%b-%Y"))
                .setNumberTicks(monthListArray.length) //
                )
                //
                .addAxis(
                new XYaxis(XYaxes.Y)
                .setMin(minTickValue)
                .setTickInterval(tickInterval)
                .setTickOptions(
                new AxisTickRenderer()
                .setFormatString("%.2f")) // .setFormatString("R%.2f")
                );

        Highlighter highlighter = new Highlighter()
                .setShow(true)
                .setSizeAdjust(10)
                .setTooltipLocation(TooltipLocations.NORTH)
                .setTooltipAxes(TooltipAxes.Y)
                .setTooltipFormatString("<b><i><span style='color:red;'>Value:</span></i></b> %.2f")
                .setUseAxesFormatters(false);

        Cursor cursor = new Cursor()
                .setShow(true);

        Title title = new Title("");
        title.setFontSize("13pt");
        title.setTextAlign(TextAligns.LEFT);
        title.setShow(true);

        if (chartTitle != null) {
            title.setText(chartTitle);
        }

        Options options = new Options()
                //                .addOption(seriesDefaults)
                .setTitle(title)
                .setSeries(series)
                .setAxesDefaults(axesDefaults)
                .addOption(axes)
                .addOption(highlighter)
                .addOption(cursor);

        DCharts chart = new DCharts()
                .setDataSeries(dataSeries)
                .setOptions(options) //	.show()
                ;
        return chart;
    }
}
