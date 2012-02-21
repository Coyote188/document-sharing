package com.dss.storage.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Configurable;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentContentBean;
import com.dss.storage.repository.util.FileUtil;
import com.dss.util.log.LogUtil;

@Configurable
@SuppressWarnings("unchecked")
public class DocumentContent
        implements DocumentContentBean
{
    @Resource
    private FileUtil fileUtil;
    private byte[] file;
    private byte[] preImage;
    private String picMd5;
    private String name;

    private PDDocument document;
    private PDDocumentCatalog catalog;

    public DocumentContent(String name, byte[] file) {
        this.name = name;
        // setFile(file);
        this.file = file;
        // imgList = new ArrayList<BufferedImage>();
    }

    public DocumentContent(String name, String picMd5) {
        this.name = name;
        this.picMd5 = picMd5;
        // imgList = new ArrayList<BufferedImage>();
    }

    public String save()
    {
        String md5=null;
        try {
            setPicMd5(savePreviewPicture());
            md5=fileUtil.saveFile(name, FileUtil.FILE_STORE_LOCATION,IOUtils.toByteArray(getFile()));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return md5;
    }

    public void load(String md5)
    {
        try {
            file=IOUtils.toByteArray(fileUtil.getFile(md5, FileUtil.FILE_STORE_LOCATION));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void unSave()
    {
        fileUtil.removeFile(name, FileUtil.FILE_STORE_LOCATION);
    }

    /**
     * get preview image of doc by md5
     * 
     * @param md5
     */
    public void loadPreviewImage(String md5)
    {
        setPreviewPicture(fileUtil.getFile(md5, FileUtil.DOCPREIMAGE_STORE_LOCATION));
    }

    /**
     * save preview picture convert
     * 
     * @return
     * @throws IOException
     */
    public String savePreviewPicture()
            throws IOException
    {
        // document = PDDocument.load(file);
        // catalog = document.getDocumentCatalog();
        // List pages = catalog.getAllPages();
        // PDPage page = (PDPage) pages.get(1);
        // BufferedImage image = page.convertToImage();
        BufferedImage image = getPage(1);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream is = ImageIO.createImageOutputStream(bs);
        ImageIO.write(image, "jpg", is);
        setPreviewPicture(new ByteArrayInputStream(bs.toByteArray()));
        return fileUtil.saveFile(name, FileUtil.DOCPREIMAGE_STORE_LOCATION, IOUtils.toByteArray(getPreviewPicture()));
    }

    private void setPreviewPicture(InputStream s)
    {
        try {
            this.preImage = IOUtils.toByteArray(s);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private InputStream getPreviewPicture()
    {
        return new ByteArrayInputStream(this.preImage);
    }

    public InputStream getFile()
    {
        // return new ByteArrayInputStream(this.file.getBytes());
        return new ByteArrayInputStream(this.file);
    }

//    private void setFile(InputStream file)
//    {
//        try {
//            this.file = IOUtils.toString(file);
//            document = PDDocument.load(getFile());
//            catalog = document.getDocumentCatalog();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    public List<BufferedImage> getAllPages()
    {
        // imgList= LazyList.decorate(new ArrayList<BufferedImage>(), new
        // Factory() {
        //            
        // @Override
        // public Object create()
        // {
        // // TODO Auto-generated method stub
        // return null;
        // }
        // });
        List<BufferedImage> imgList = new ArrayList<BufferedImage>();
        try {
            document = PDDocument.load(getFile());
            catalog = document.getDocumentCatalog();
            // if (!(imgList.size() == catalog.getAllPages().size())) {
            // for (int i = 0; i < catalog.getAllPages().size(); i++) {
            // imgList.add(getPage(i));
            // }
            // }
            List pages = catalog.getAllPages();
            for (int i = 0; i < pages.size(); i++) {
                PDPage page = (PDPage) pages.get(i);
                BufferedImage image = page.convertToImage();
                imgList.add(image);
            }
            document.close();
        } catch (IOException e) {
            LogUtil.error(getClass(), e.getCause());
        }
        // document.close();
        return imgList;
    }

    @Override
    public BufferedImage getPage(int number)
    {
        BufferedImage image = null;
        try {

            document = PDDocument.load(getFile());
            catalog = document.getDocumentCatalog();
            List pages = catalog.getAllPages();
            PDPage page = (PDPage) pages.get(number);
            image = page.convertToImage();
            document.close();
        } catch (IOException e) {
            LogUtil.error(getClass(), e.getCause());
        }
        return image;
    }

    public BufferedImage getPreviewImage()
    {
        BufferedImage img = null;
        // Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
        // ImageReader reader = (ImageReader) readers.next();
        // ImageInputStream iis;
        // try {
        // iis = ImageIO.createImageInputStream(this.file);
        // reader.setInput(iis, true);
        // img = reader.read(0);
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // return img;
        try {
            img = ImageIO.read(getPreviewPicture());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return img;
    }

    public void setPicMd5(String picMd5)
    {
        this.picMd5 = picMd5;
    }

    public String getPicMd5()
    {
        return picMd5;
    }

}
