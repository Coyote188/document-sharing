package com.dss.storage.bean;

import java.awt.image.BufferedImage;
import java.util.List;

public interface DocumentContentBean
{

   List<BufferedImage> getAllPages();
   BufferedImage getPage(int number);
}