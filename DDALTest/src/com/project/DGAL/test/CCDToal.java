package com.project.DGAL.test;

import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;

public class CCDToal {

	public String productCCD(String path)
	{   
		//gdal.AllRegister();	
		CCDToal_HJ ccd_HJ=new CCDToal_HJ();
		String result=ccd_HJ.productCCD_HJ(path);
		
		return result;
	}
}
