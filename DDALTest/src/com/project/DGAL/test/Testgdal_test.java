package com.project.DGAL.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.gdal.gdal.Band;
import org.gdal.gdal.Dataset;  
import org.gdal.gdal.Driver;  
import org.gdal.gdal.GCP;
import org.gdal.gdal.gdal;  
import org.gdal.gdalconst.gdalconstConstants;
public class Testgdal_test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����git
		System.out.println("test git");
		String fileName_tif = "G:\\NDVI.tif";   
		//String fileName_tif = "F:\\11.TIF"; 
        gdal.AllRegister();  
  
        Dataset hDataset = gdal.Open(fileName_tif, gdalconstConstants.GA_ReadOnly);  
        if (hDataset == null)  
        {  
            System.err.println("GDALOpen failed - " + gdal.GetLastErrorNo());  
            System.err.println(gdal.GetLastErrorMsg());  
  
            System.exit(1);  
        }  
  
        
        Driver hDriver = hDataset.GetDriver();  
        System.out.println("Driver: " + hDriver.getShortName() + "/" + hDriver.getLongName());  
        System.out.println(gdal.GetDriverCount());
        
  
        int iXSize=hDataset.getRasterXSize();//ͼ����
        int iYSize=hDataset.getRasterYSize();//ͼ��߶�
        
        int rasterCount = hDataset.getRasterCount();//Geotiff�еĲ�������HDF�ļ����ض������ݼ��еĲ�����
        String projectionRef = hDataset.GetProjectionRef();//ͼ�����ο�
        String gcpProjection = hDataset.GetGCPProjection();//GCP����
        double[] getGeoTransform = hDataset.GetGeoTransform();//GEO����
        Vector<GCP> getGCPs=new Vector<GCP>(hDataset.GetGCPCount());
        getGCPs = hDataset.GetGCPs();//GCP�б�
        
         System.out.println(hDataset.GetGCPCount());
         System.out.println(getGCPs.toArray().length);;
        GCP[] gcpS=new GCP[hDataset.GetGCPCount()];
        for(int i=0;i<hDataset.GetGCPCount();i++){
        	gcpS[i]=getGCPs.get(i);
        }
        
        for(double d:getGeoTransform){
        	System.out.print(d+"  ");
        }
        
        
        String metadataItem = hDataset.GetMetadataItem("");//Ĭ��Ԫ����
        String metadataItem2 = hDataset.GetMetadataItem("IMAGE_STRUCTURE");//IMAGE_STRUCTUREԪ����
        String metadataItem3 = hDataset.GetMetadataItem("SUBDATASETS");//SUBDATASETSԪ����
        String metadataItem4 = hDataset.GetMetadataItem("GEOLOCATION");//GEOLOCATIONԪ����
        
        System.out.println("\nSize is :\nXSize: " + iXSize + ", \nYSize: "  + iYSize+",\nrasterCount: "+rasterCount+",\nprojectionRef: "+projectionRef+",\ngcpProjection: "+gcpProjection+",\ngetGeoTransform: "+getGeoTransform.length+"\nimageGCPList: "+gcpS.length+" \n#"+metadataItem+" #"+metadataItem2+" #"+metadataItem3+" #"+metadataItem4); 
        Band band = hDataset.GetRasterBand(1);
        
       // int[] buf=new int[iXSize];
        byte[] buf=new byte[iXSize];
        for(int i=0;i<10;i++){//iYSize
        	band.ReadRaster(0, i, iXSize, 1, buf);
        	for(int j=0;j<10;j++){//iXSize
        		System.out.print(buf[j]+" ");
        	}
        	System.out.println();
        }
        System.out.println("####");
        float[] buff=new float[iXSize*iYSize];
        band.ReadRaster(0, 0, iXSize, iYSize, buff);
        System.out.println(buff.length);
        for(int k=0;k<10;k++){
       	 System.out.print(buff[k]+" ");
        } 
        hDataset.FlushCache();
        band.delete();
        hDataset.delete();  
        
        /*Driver getDriver = gdal.GetDriverByName("GTiff");
        String name="F:\\11.TIF";
        
        long beginTime = System.currentTimeMillis();
        for(int j=0;j<buff.length;j++){
        	if(buff[j]==0.0){
        		buff[j]=0;
        		continue;
        	}
        	buff[j]=buff[j]/0.7726f+3.0089f;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\ndddd11:::"+(endTime-beginTime));
        
        long beginTime2 = System.currentTimeMillis();
        List<Integer> aa=new ArrayList<Integer>();
        for(int j=0;j<buff.length;j++){
        	if(buff[j]==0.0){
        		aa.add(j);
        	}
        	buff[j]=buff[j]/0.7726f+3.0089f;
        }
        
        for(int k=0;k<aa.size();k++){
        	buff[aa.get(k)]=0;
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("\ndddd22:::"+(endTime2-beginTime2));
        //Dataset dataSet = getDriver.Create(name, iXSize, iYSize, 1, null);
        Dataset dataSet =getDriver.Create(name, iXSize, iYSize, 1, gdalconstConstants.GDT_Float32);
        dataSet.SetDescription(name);
        dataSet.SetProjection(projectionRef);
        dataSet.SetGeoTransform(getGeoTransform);
        dataSet.SetGCPs(gcpS, gcpProjection);
        dataSet.SetMetadata(metadataItem);
        dataSet.SetMetadata(metadataItem2);
        dataSet.SetMetadata(metadataItem3);
        dataSet.SetMetadata(metadataItem4);
       // Band band2 = dataSet.GetRasterBand(1);
        //band2.WriteRaster(0, 0, iXSize, iYSize, buff);
       dataSet.WriteRaster(0, 0, iXSize, iYSize, iXSize, iYSize, gdalconstConstants.GDT_Float32, buff, null);
        dataSet.FlushCache();
       // band2.delete();
        dataSet.delete();*/
        // ��ѡ  
        gdal.GDALDestroyDriverManager();  

	}

}
