package com.ColorIdentification;
import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.List;
import java.util.Vector;

public class OpenCV {

    public void OpenCVCI(String image) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mat = Imgcodecs.imread(image);//读图片
        Mat hsv_image = new Mat(mat.size(), mat.type());
        Imgproc.cvtColor(mat, hsv_image, Imgproc.COLOR_BGR2HSV);
        Imgcodecs.imwrite("b.png", hsv_image);
        Imgcodecs.imwrite("c.png", mat);
        int num_rows = hsv_image.rows();
        int num_col = hsv_image.cols();
        for (int i = 0; i < num_rows; i++) {
            for (int j = 0; j < num_col; j++) {
                // 获取每个像素
                double[] clone = hsv_image.get(i, j).clone();
                double hun = clone[0]; // HSV hun

                if ((hun >= 0 && hun < 34) || (hun > 160 && hun < 200)) {
                    if (clone[1] > 25 && clone[1] < 240) {
                        if (clone[2] < 200 && clone[2] > 90) {
                            // 红色范围,全部设置为黑色,
                            clone[0] = 0;
                            clone[1] = 0;
                            clone[2] = 0;
                            hsv_image.put(i, j, clone);
                            Imgcodecs.imwrite("a.png", hsv_image);
                        } else {
                            clone[0] = 255;
                            clone[1] = 255;
                            clone[2] = 255;
                            hsv_image.put(i, j, clone);
                        }
                    }
                }
            }
        }
    }
}
