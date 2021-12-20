/**
 * Copyright(C),2015‐2021,北京清能互联科技有限公司
 */
package com.wangh.consumer.data;

import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;

/**
 * Description: 数据 <br>
 *
 * @Author: wanghao@tsintergy.com
 * @Date: 2021/12/8 13:51
 * @Version: 1.0.0
 */
public class DataSource {

    public DefaultTableXYDataset getData() {

        XYSeries localTimeSeries1 = new XYSeries("示例曲线1", false, false);
        for (int i = 1; i < 97; i++) {
            int temp = (int) (Math.random() * 100);
            localTimeSeries1.add(i, temp);
        }

        XYSeries localTimeSeries2 = new XYSeries("示例曲线2", false, false);
        for (int i = 1; i < 97; i++) {
            int temp = (int) (Math.random() * 100);
            localTimeSeries2.add(i, temp);
        }

        DefaultTableXYDataset localTimeSeriesCollection = new DefaultTableXYDataset();
        localTimeSeriesCollection.addSeries(localTimeSeries1);
        localTimeSeriesCollection.addSeries(localTimeSeries2);

        return localTimeSeriesCollection;


    }


}