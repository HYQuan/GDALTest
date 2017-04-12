package com.project.DGAL.test;

import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;

public class GeoInfo {

	 /// <summary>
    /// ͼ����
    /// </summary>
    public int RasterXSize ;
    /// <summary>
    /// ͼ��߶�
    /// </summary>
    public int RasterYSize ;
    /// <summary>
    /// ����ο�
    /// </summary>
    public String ProjectionRef ;
    /// <summary>
    /// GCP����
    /// </summary>
    public String GCPProjection;
    /// <summary>
    /// geo����
    /// </summary>
    public double[] GeoTransform ;
    /// <summary>
    /// GCP�б�
    /// </summary>
    //public GCP[] GCPList;
    /// <summary>
    /// Ĭ��Ԫ����
    /// </summary>
    public String DefaultMetaData;
    /// <summary>
    ///IMAGE_STRUCTUREԪ����
    /// </summary>
    public String ImageStructureMetaData;

    /// <summary>
    ///SUBDATASETSԪ����
    /// </summary>
    public String SubdatasetsMetaData;
    /// <summary>
    ///GEOLOCATIONԪ����
    /// </summary>
    public String GeolocationMetaData;
	
    public void getInfo(String path)
    {
    	gdal.AllRegister();
    	Dataset hDataset=gdal.Open(path,gdalconstConstants.GA_ReadOnly);
    	RasterXSize=hDataset.getRasterXSize();
    	RasterYSize=hDataset.getRasterYSize();
    	ProjectionRef = hDataset.GetProjectionRef().toString();
    	GCPProjection = hDataset.GetGCPProjection();
    	double[] geoTransform = new double[6];
    	hDataset.GetGeoTransform(geoTransform);
    	GeoTransform=geoTransform;
    	DefaultMetaData = hDataset.GetMetadataItem("");//Ĭ��Ԫ����
    	ImageStructureMetaData=hDataset.GetMetadataItem("IMAGE_STRUCTURE");//IMAGE_STRUCTUREԪ����
    	SubdatasetsMetaData=hDataset.GetMetadataItem("SUBDATASETS");//SUBDATASETSԪ����
    	GeolocationMetaData=hDataset.GetMetadataItem("GEOLOCATION");//GEOLOCATIONԪ����

    	hDataset.delete();
    }
}
